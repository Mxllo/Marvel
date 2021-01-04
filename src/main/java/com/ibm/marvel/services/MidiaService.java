package com.ibm.marvel.services;

import com.ibm.marvel.dtos.id.FilmeIdDTO;
import com.ibm.marvel.dtos.id.MidiaIdDTO;
import com.ibm.marvel.dtos.id.RevistaIdDTO;
import com.ibm.marvel.dtos.insert.MidiaNewDTO;
import com.ibm.marvel.model.*;
import com.ibm.marvel.repositories.MidiaRepository;
import com.ibm.marvel.services.exception.DataIntegrityException;
import com.ibm.marvel.services.exception.ObjectDuplicationException;
import com.ibm.marvel.services.exception.ObjectNotFoundException;
import com.ibm.marvel.services.helper.MidiaHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static com.ibm.marvel.parser.MidiaParser.*;

@Service
public class MidiaService {

    @Autowired
    private MidiaRepository repo;
    @Autowired
    private MidiaHelper helper;

    public Midia find(Integer id) {
        Optional<Midia> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Midia.class.getName()));
    }

    public MidiaNewDTO insertFilme(MidiaNewDTO novaMidia) {
        checkExists(novaMidia);
        repo.save(helper.parseMidiaFilme(novaMidia));
        novaMidia.setId(Integer.parseInt(String.valueOf(repo.count())));
        return novaMidia;
    }

    public MidiaNewDTO insertRevista(MidiaNewDTO novaMidia) {
        checkExists(novaMidia);
        repo.save(helper.parseMidiaRevista(novaMidia));
        novaMidia.setId(Integer.parseInt(String.valueOf(repo.count())));
        return novaMidia;
    }

    public void updateFilme(MidiaNewDTO obj) {
        find(obj.getId());
        repo.save(helper.parseMidiaFilmeComId(obj));
    }

    public void updateRevista(MidiaNewDTO obj) {
        find(obj.getId());
        repo.save(helper.parseMidiaRevistaComId(obj));
    }

    @Transactional
    public void delete(Integer id) {
        find(id);
        try {
            repo.deleteById(id);
        } catch (
                DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não foi possivel excluir a Midia.");
        }
    }

    public List<Midia> findAll() {
        return repo.findAll();
    }


    public Midia findByNome(String nome) {
        Optional<Midia> obj = repo.findByNome(nome);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Nome: " + nome + ", Tipo: " + Criador.class.getName()));
    }


    public MidiaIdDTO createMidia(Midia midia){
        if(parseTipo(midia).equals("Filme")){
            return new FilmeIdDTO(midia);
        }else return new RevistaIdDTO(midia);
    }

    private void checkExists(MidiaNewDTO obj) {
        if(repo.findByNome(obj.getNome()).isPresent())
            throw new ObjectDuplicationException("Midia já existente!");
    }
}
