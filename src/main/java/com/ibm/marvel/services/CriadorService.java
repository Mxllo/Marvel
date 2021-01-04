package com.ibm.marvel.services;

import com.ibm.marvel.dtos.insert.AtorNewDTO;
import com.ibm.marvel.model.Criador;
import com.ibm.marvel.repositories.CriadorRepository;
import com.ibm.marvel.services.exception.DataIntegrityException;
import com.ibm.marvel.services.exception.DataViolationException;
import com.ibm.marvel.services.exception.ObjectDuplicationException;
import com.ibm.marvel.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.ibm.marvel.services.helper.CriadorHelper.checkMaxCreations;

@Service
public class CriadorService {

    @Autowired
    private CriadorRepository repo;


    public Criador find(Integer id) {
        Optional<Criador> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Criador.class.getName()));
    }

    public Criador insert(Criador obj) {
        obj.setId(null);
        checkExists(obj);
        return repo.save(obj);
    }

    public Criador update(Criador obj) {
        find(obj.getId());
        return repo.save(obj);
    }

    public void delete(Integer id) {
        find(id);
        try {
            repo.deleteById(id);
        } catch (
                DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não foi possivel excluir a Criador, a mesma possui produtos.");
        }
    }

    public List<Criador> findAll() {
        return repo.findAll();
    }

    public Criador findByNome(String nome) {
        Optional<Criador> obj = repo.findByNome(nome);
        checkMaxCreations(obj);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Nome: " + nome + ", Tipo: " + Criador.class.getName()));
    }

    private void checkExists(Criador obj) {
        if(repo.findByNome(obj.getNome()).isPresent())
            throw new ObjectDuplicationException("Criador já existente!");
    }
}
