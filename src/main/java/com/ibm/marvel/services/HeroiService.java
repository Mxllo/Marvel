package com.ibm.marvel.services;

import com.ibm.marvel.dtos.insert.HeroiNewDTO;
import com.ibm.marvel.model.Criador;
import com.ibm.marvel.model.Heroi;
import com.ibm.marvel.model.Midia;
import com.ibm.marvel.model.Poder;
import com.ibm.marvel.repositories.HeroiRepository;
import com.ibm.marvel.services.exception.DataIntegrityException;
import com.ibm.marvel.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.ibm.marvel.services.parser.HeroiParser.parseMidia;
import static com.ibm.marvel.services.parser.HeroiParser.parsePoder;

@Service
public class HeroiService implements Serializable {
    private static final long serialVersionUID = 1L;

    @Autowired
    private HeroiRepository repo;
    @Autowired
    private CriadorService criadorService;
    @Autowired
    private AtorService atorService;
    @Autowired
    private PoderService poderService;
    @Autowired
    private MidiaService midiaService;

    public Heroi find(Integer id) {
        Optional<Heroi> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Heroi.class.getName()));
    }

    public HeroiNewDTO insert(HeroiNewDTO novoHeroi) {
        repo.save(parseHeroi(novoHeroi));
        novoHeroi.setId(Integer.parseInt(String.valueOf(repo.count())));
        return novoHeroi;
    }

    public HeroiNewDTO update(HeroiNewDTO obj) {
        find(obj.getId());
        repo.save(parseHeroiComId(obj));
        return obj;
    }

    public void delete(Integer id) {
        find(id);
        try {
            repo.deleteById(id);
        } catch (
                DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não foi possivel excluir a Heroi, a mesma possui produtos.");
        }
    }

    public List<Heroi> findAll() {
        return repo.findAll();
    }

    private Heroi parseHeroi(HeroiNewDTO novoHeroi){
        return new Heroi(null, novoHeroi.getNome(), novoHeroi.getOrigem(),
                parsePoder(novoHeroi.getPoderes()),
                atorService.findByNome(novoHeroi.getAtor()),
                criadorService.findByNome(novoHeroi.getCriador()),
                parseMidia(novoHeroi.getMidias()));
    }

    private Heroi parseHeroiComId(HeroiNewDTO novoHeroi){
        return new Heroi(novoHeroi.getId(), novoHeroi.getNome(), novoHeroi.getOrigem(),
                parsePoder(novoHeroi.getPoderes()),
                atorService.findByNome(novoHeroi.getAtor()),
                criadorService.findByNome(novoHeroi.getCriador()),
                parseMidia(novoHeroi.getMidias()));
    }

    public Set<Poder> parsePoder(Set<String> poderesString){
        Set<Poder> poderes = new HashSet<>();
        System.out.println(poderesString);
        poderesString.forEach((poder) -> {
            poderes.add(poderService.findByNome(poder));
        });
        return poderes;
    }

    public Set<Midia> parseMidia(Set<String> midiasString){
        Set<Midia> midias = new HashSet<>();
        midiasString.forEach((midia) -> {
            midias.add(midiaService.findByNome(midia));
        });
        return midias;
    }

    public Heroi findByNome(String heroi) {
        Optional<Heroi> obj = repo.findByNome(heroi);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Nome: " + heroi + ", Tipo: " + Criador.class.getName()));
    }
}
