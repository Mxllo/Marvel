package com.ibm.marvel.services;

import com.ibm.marvel.dtos.insert.AtorNewDTO;
import com.ibm.marvel.model.Ator;
import com.ibm.marvel.repositories.AtorRepository;
import com.ibm.marvel.services.exception.DataIntegrityException;
import com.ibm.marvel.services.exception.ObjectDuplicationException;
import com.ibm.marvel.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.ibm.marvel.parser.AtorParser.parseAtor;


@Service
public class AtorService {

    @Autowired
    private AtorRepository repo;

    public Ator find(Integer id) {
        Optional<Ator> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Ator.class.getName()));
    }

    public AtorNewDTO insert(AtorNewDTO obj) {
        obj.setId(null);
        checkExists(obj);
        repo.save(parseAtor(obj) );
        return obj;
    }

    private void checkExists(AtorNewDTO obj) {
        if(repo.findByNome(obj.getNome()).isPresent())
            throw new ObjectDuplicationException("Ator já existente!");
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



}
