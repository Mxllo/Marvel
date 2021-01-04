package com.ibm.marvel.services.helper;

import com.ibm.marvel.dtos.insert.HeroiNewDTO;
import com.ibm.marvel.model.Heroi;
import com.ibm.marvel.model.Poder;
import com.ibm.marvel.services.AtorService;
import com.ibm.marvel.services.CriadorService;
import com.ibm.marvel.services.MidiaService;
import com.ibm.marvel.services.PoderService;
import com.ibm.marvel.services.exception.DataViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Service
public class HeroiHelper implements Serializable {
    private static final long serialVersionUID = 1L;

    @Autowired
    private CriadorService criadorService;
    @Autowired
    private AtorService atorService;
    @Autowired
    private PoderService poderService;


    public Heroi parseHeroi(HeroiNewDTO novoHeroi){
        return new Heroi(null, novoHeroi.getNome(), novoHeroi.getOrigem(),
                parsePoder(novoHeroi.getPoderes()),
                atorService.findByNome(novoHeroi.getAtor()),
                criadorService.findByNome(novoHeroi.getCriador()));
    }

    public Heroi parseHeroiComId(HeroiNewDTO novoHeroi){
        return new Heroi(novoHeroi.getId(), novoHeroi.getNome(), novoHeroi.getOrigem(),
                parsePoder(novoHeroi.getPoderes()),
                atorService.findByNome(novoHeroi.getAtor()),
                criadorService.findByNome(novoHeroi.getCriador()));
    }

    public Set<Poder> parsePoder(Set<String> poderesString){
        Set<Poder> poderes = new HashSet<>();
        poderesString.forEach((poder) -> {
            poderes.add(poderService.findByNome(poder));
            if(poderes.size()>5)
                throw new DataViolationException("O Herói possui mais de 5 poderes");
        });
        return poderes;
    }
}
