package com.ibm.marvel.services;

import com.ibm.marvel.dtos.insert.PoderNewDTO;
import com.ibm.marvel.model.Poder;
import com.ibm.marvel.repositories.PoderRepository;
import com.ibm.marvel.services.exception.DataIntegrityException;
import com.ibm.marvel.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PoderService {

    @Autowired
    private PoderRepository repo;
    @Autowired
    private CriadorService criadorService;

    public Poder find(Integer id) {
        Optional<Poder> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Poder.class.getName()));
    }

    public PoderNewDTO insert(PoderNewDTO novoPoder) {
        repo.save(parsePoder(novoPoder));
        novoPoder.setId(Integer.parseInt(String.valueOf(repo.count())));
        return novoPoder;
    }

    public PoderNewDTO update(PoderNewDTO obj) {
        find(obj.getId());
        repo.save(parsePoderComId(obj));
        return obj;
    }

    public void delete(Integer id) {
        find(id);
        try {
            repo.deleteById(id);
        } catch (
                DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não foi possivel excluir a Poder, a mesma possui produtos.");
        }
    }

    public List<Poder> findAll() {
        return repo.findAll();
    }

    private Poder parsePoder(PoderNewDTO novoPoder){
        return new Poder(null, novoPoder.getNome(), criadorService.findByNome(novoPoder.getCriador()));
    }

    private Poder parsePoderComId(PoderNewDTO novoPoder) {
        return new Poder(novoPoder.getId(), novoPoder.getNome(), criadorService.findByNome(novoPoder.getCriador()));
    }

}
