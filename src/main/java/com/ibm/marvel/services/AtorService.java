package com.ibm.marvel.services;

import com.ibm.marvel.dtos.AtorDTO;
import com.ibm.marvel.dtos.id.AtorIdDTO;
import com.ibm.marvel.dtos.insert.AtorNewDTO;
import com.ibm.marvel.dtos.insert.PoderNewDTO;
import com.ibm.marvel.model.Ator;
import com.ibm.marvel.model.Poder;
import com.ibm.marvel.repositories.AtorRepository;
import com.ibm.marvel.services.exception.DataIntegrityException;
import com.ibm.marvel.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AtorService {

    @Autowired
    private AtorRepository repo;
    @Autowired
    private CriadorService Service;


    public Ator find(Integer id) {
        Optional<Ator> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Ator.class.getName()));
    }

    public AtorNewDTO insert(AtorNewDTO obj) {
        obj.setId(null);
        repo.save(parseAtor(obj));
        return obj;
    }
    
    public Ator update(Ator obj) {
        find(obj.getId());
        return repo.save(obj);
    }

    public void delete(Integer id) {
        find(id);
        try {
            repo.deleteById(id);
        } catch (
                DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não foi possivel excluir a Ator, a mesma possui produtos.");
        }
    }

    public List<Ator> findAll() {
        return repo.findAll();
    }

    public Ator findByNome(String nome) {
        Optional<Ator> obj = repo.findByNome(nome);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Nome: " + nome + ", Tipo: " + Ator.class.getName()));
    }

    private Ator parseAtor(AtorNewDTO novoAtor){
        return new Ator(null, novoAtor.getNome(), novoAtor.getDataDeNascimento());
    }

    //private Poder parsePoderComId(PoderNewDTO novoPoder) {
    //    return new Poder(novoPoder.getId(), novoPoder.getNome(), criadorService.findByNome(novoPoder.getCriador()));
    //}
}
