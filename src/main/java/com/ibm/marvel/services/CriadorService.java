package com.ibm.marvel.services;

import com.ibm.marvel.model.Criador;
import com.ibm.marvel.repositories.CriadorRepository;
import com.ibm.marvel.services.exception.DataIntegrityException;
import com.ibm.marvel.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
}
