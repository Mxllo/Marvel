package com.ibm.marvel.parser;

import com.ibm.marvel.dtos.CriadorDTO;
import com.ibm.marvel.dtos.HeroiDTO;
import com.ibm.marvel.model.Filme;
import com.ibm.marvel.model.Midia;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class MidiaParser implements Serializable {
    private static final long serialVersionUID = 1L;

    public static Set<HeroiDTO> parseHeroi(Midia midia){
        Set<HeroiDTO> heroes = new HashSet<>();
        midia.getHerois().forEach((heroi) -> {
            heroes.add(new HeroiDTO(heroi.getId(), heroi.getNome()));
        });
        return heroes;
    }

    public static CriadorDTO parseCriador (Midia midia){
        return new CriadorDTO(midia.getCriador().getId(), midia.getCriador().getNome());
    }

    public static String classDefiner(Object obj){
        if (obj.getClass().toString().equals("class com.ibm.marvel.model.Filme")){
            return "Filme";
        }else{
            return "Revista";
        }
    }

    public static Integer horaParser(String hora){
        return Integer.parseInt(hora.substring(0,hora.indexOf(":")));
    }

    public static Integer minutoParser(String hora){
        return Integer.parseInt(hora.substring(hora.indexOf(":")+1));
    }

    public static String parseTipo(Midia midia){
        if(midia.getClass().toString().equals(Filme.class.toString())){
            return "Filme";
        }else
            return "Revista";
    }
}
