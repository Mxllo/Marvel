package com.ibm.marvel.services.parser;

import com.ibm.marvel.dtos.CriadorDTO;
import com.ibm.marvel.dtos.HeroiDTO;
import com.ibm.marvel.dtos.MidiaDTO;
import com.ibm.marvel.dtos.PoderDTO;
import com.ibm.marvel.dtos.insert.PoderNewDTO;
import com.ibm.marvel.model.Criador;
import com.ibm.marvel.model.Poder;
import com.ibm.marvel.services.CriadorService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class PoderParser implements Serializable {
    private static final long serialVersionUID = 1L;

    public static Set<HeroiDTO> parseHeroi(Poder poder){
        Set<HeroiDTO> heroes = new HashSet<>();
        poder.getHerois().forEach((heroi) -> {
            heroes.add(new HeroiDTO(heroi.getId(), heroi.getNome()));
        });
        return heroes;
    }

    public static CriadorDTO parseCriador (Poder poder){
        return new CriadorDTO(poder.getCriador().getId(), poder.getCriador().getNome());
    }

}
