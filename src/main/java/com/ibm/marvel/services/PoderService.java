package com.ibm.marvel.services;

import com.ibm.marvel.dtos.PoderDTO;
import com.ibm.marvel.dtos.insert.PoderNewDTO;
import com.ibm.marvel.model.Criador;
import com.ibm.marvel.model.Poder;
import com.ibm.marvel.repositories.PoderRepository;
import com.ibm.marvel.services.exception.DataIntegrityException;
import com.ibm.marvel.services.exception.ObjectDuplicationException;
import com.ibm.marvel.services.exception.ObjectNotFoundException;
import com.ibm.marvel.services.helper.PoderHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PoderService implements Serializable {
    private static final long serialVersionUID = 1L;

    @Autowired
    private PoderRepository repo;
    @Autowired
    private PoderHelper helper;

    public Poder find(Integer id) {
        Optional<Poder> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Poder.class.getName()));
    }

    public PoderNewDTO insert(PoderNewDTO novoPoder) {
        checkExists(novoPoder);
        repo.save(helper.parsePoder(novoPoder));
        novoPoder.setId(Integer.parseInt(String.valueOf(repo.count())));
        return novoPoder;
    }

    public PoderNewDTO update(PoderNewDTO obj) {
        find(obj.getId());
        repo.save(helper.parsePoderComId(obj));
        return obj;
    }

    public void delete(Integer id) {
        find(id);
        try {
            repo.deleteById(id);
        } catch (
                DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não foi possivel excluir a Poder, um heroi possui esse poder.");
        }
    }

    public List<Poder> findAll() {
        return repo.findAll();
    }



    public Poder findByNome(String nome) {
        Optional<Poder> obj = repo.findByNome(nome);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Nome: " + nome + ", Tipo: " + Criador.class.getName()));
    }


    private void checkExists(PoderNewDTO obj) {
        if(repo.findByNome(obj.getNome()).isPresent())
            throw new ObjectDuplicationException("Poder já existente!");
    }
}
