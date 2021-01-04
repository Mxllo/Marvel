package com.ibm.marvel.services;

import com.ibm.marvel.dtos.id.FilmeIdDTO;
import com.ibm.marvel.dtos.id.MidiaIdDTO;
import com.ibm.marvel.dtos.id.RevistaIdDTO;
import com.ibm.marvel.dtos.insert.MidiaNewDTO;
import com.ibm.marvel.model.*;
import com.ibm.marvel.model.enums.ClassificacaoIndicativa;
import com.ibm.marvel.repositories.MidiaRepository;
import com.ibm.marvel.services.exception.DataIntegrityException;
import com.ibm.marvel.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.ibm.marvel.services.parser.MidiaParser.*;

@Service
public class MidiaService {

    @Autowired
    private MidiaRepository repo;
    @Autowired
    private CriadorService criadorService;
    @Autowired
    private HeroiService heroiService;

    public Midia find(Integer id) {
        Optional<Midia> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Midia.class.getName()));
    }

    public MidiaNewDTO insertFilme(MidiaNewDTO novaMidia) {
        repo.save(parseMidiaFilme(novaMidia));
        novaMidia.setId(Integer.parseInt(String.valueOf(repo.count())));
        return novaMidia;
    }

    public MidiaNewDTO insertRevista(MidiaNewDTO novaMidia) {
        repo.save(parseMidiaRevista(novaMidia));
        novaMidia.setId(Integer.parseInt(String.valueOf(repo.count())));
        return novaMidia;
    }

    public MidiaNewDTO updateFilme(MidiaNewDTO obj) {
        find(obj.getId());
        repo.save(parseMidiaFilmeComId(obj));
        return obj;
    }

    public MidiaNewDTO updateRevista(MidiaNewDTO obj) {
        find(obj.getId());
        repo.save(parseMidiaRevistaComId(obj));
        return obj;
    }

    @Transactional
    public void delete(Integer id) {
        find(id);
        try {
            repo.deleteById(id);
        } catch (
                DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não foi possivel excluir a Midia, a mesma possui produtos.");
        }
    }

    public List<Midia> findAll() {
        return repo.findAll();
    }

    private Midia parseMidiaFilme(MidiaNewDTO novaMidia){
        return new Filme(null, criadorService.findByNome(novaMidia.getCriador()), novaMidia.getNome(),
                ClassificacaoIndicativa.valueOf(novaMidia.getClassificacao()), parseHeroi(novaMidia),
                LocalTime.of(horaParser(novaMidia.getDuracao()), minutoParser(novaMidia.getDuracao())));
    }

    private Midia parseMidiaRevista(MidiaNewDTO novaMidia){
        return new Revista(null, criadorService.findByNome(novaMidia.getCriador()), novaMidia.getNome(),
                ClassificacaoIndicativa.valueOf(novaMidia.getClassificacao()),parseHeroi(novaMidia), novaMidia.getPaginas());
    }

    private Midia parseMidiaFilmeComId(MidiaNewDTO novaMidia){
        return new Filme(novaMidia.getId(), criadorService.findByNome(novaMidia.getCriador()), novaMidia.getNome(),
                ClassificacaoIndicativa.valueOf(novaMidia.getClassificacao()), parseHeroi(novaMidia),
                LocalTime.of(horaParser(novaMidia.getDuracao()), minutoParser(novaMidia.getDuracao())));
    }

    private Midia parseMidiaRevistaComId(MidiaNewDTO novaMidia){
        return new Revista(novaMidia.getId(), criadorService.findByNome(novaMidia.getCriador()), novaMidia.getNome(),
                ClassificacaoIndicativa.valueOf(novaMidia.getClassificacao()),
                parseHeroi(novaMidia), novaMidia.getPaginas());
    }

    public Midia findByNome(String nome) {
        Optional<Midia> obj = repo.findByNome(nome);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Nome: " + nome + ", Tipo: " + Criador.class.getName()));
    }

    public Set<Heroi> parseHeroi(MidiaNewDTO novaMidia) {
        Set<Heroi> heroes = new HashSet<>();
        System.out.println(novaMidia.getHerois());
        novaMidia.getHerois().forEach((heroi) ->
                heroes.add(heroiService.findByNome(heroi)));
        System.out.println("**************************"+heroes.toString());
        return heroes;
    }

    public MidiaIdDTO createMidia(Midia midia){
        if(parseTipo(midia).equals("Filme")){
            return new FilmeIdDTO(midia);
        }else return new RevistaIdDTO(midia);
    }

}
