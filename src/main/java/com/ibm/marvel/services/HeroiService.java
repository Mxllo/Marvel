package com.ibm.marvel.services;

import com.ibm.marvel.dtos.insert.HeroiNewDTO;
import com.ibm.marvel.model.Criador;
import com.ibm.marvel.model.Heroi;
import com.ibm.marvel.repositories.HeroiRepository;
import com.ibm.marvel.services.exception.DataIntegrityException;
import com.ibm.marvel.services.exception.ObjectDuplicationException;
import com.ibm.marvel.services.exception.ObjectNotFoundException;
import com.ibm.marvel.services.helper.HeroiHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;


@Service
public class HeroiService implements Serializable {
    private static final long serialVersionUID = 1L;

    @Autowired
    private HeroiRepository repo;
    @Autowired
    private HeroiHelper helper;

    public Heroi find(Integer id) {
        Optional<Heroi> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Heroi.class.getName()));
    }

    public HeroiNewDTO insert(HeroiNewDTO novoHeroi) {
        checkExists(novoHeroi);
        repo.save(helper.parseHeroi(novoHeroi));
        novoHeroi.setId(Integer.parseInt(String.valueOf(repo.count())));
        return novoHeroi;
    }

    public void update(HeroiNewDTO obj) {
        find(obj.getId());
        repo.save(helper.parseHeroiComId(obj));
    }

    public void delete(Integer id) {
        find(id);
        try {
            repo.deleteById(id);
        } catch (
                DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não foi possivel excluir a Heroi, o mesmo está em uma mídia.");
        }
    }

    public List<Heroi> findAll() {
        return repo.findAll();
    }


    private void checkExists(HeroiNewDTO obj) {
        if(repo.findByNome(obj.getNome()).isPresent())
            throw new ObjectDuplicationException("Criador já existente!");
    }

    public Heroi findByNome(String heroi) {
        Optional<Heroi> obj = repo.findByNome(heroi);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Nome: " + heroi + ", Tipo: " + Criador.class.getName()));
    }
}
