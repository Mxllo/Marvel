package com.ibm.marvel.parser;

import com.ibm.marvel.dtos.HeroiDTO;
import com.ibm.marvel.dtos.MidiaDTO;
import com.ibm.marvel.dtos.PoderDTO;
import com.ibm.marvel.model.Criador;

import java.util.HashSet;
import java.util.Set;

import static com.ibm.marvel.parser.MidiaParser.classDefiner;

public class CriadorParser {

    public static Set<HeroiDTO> parseHeroi(Criador criador){
        Set<HeroiDTO> heroes = new HashSet<>();
        criador.getHerois().forEach((heroi) -> {
            heroes.add(new HeroiDTO(heroi.getId(), heroi.getNome()));
        });
        return heroes;
    }

    public static Set<PoderDTO> parsePoder (Criador criador){
        Set<PoderDTO> powers = new HashSet<>();
        criador.getPoderes().forEach((poder) -> {
            powers.add(new PoderDTO(poder.getId(), poder.getNome()));
        });
        return powers;
    }

    public static Set<MidiaDTO> parseMidia (Criador criador){
        Set<MidiaDTO> medias = new HashSet<>();
        criador.getMidias().forEach((midia) -> {
            medias.add(new MidiaDTO(midia.getId(), midia.getNome(), classDefiner(midia)));
        });
        return medias;
    }

}
