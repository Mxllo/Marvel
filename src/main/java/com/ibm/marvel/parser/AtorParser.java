package com.ibm.marvel.parser;

import com.ibm.marvel.dtos.HeroiDTO;
import com.ibm.marvel.dtos.insert.AtorNewDTO;
import com.ibm.marvel.model.Ator;

public class AtorParser {

    public static HeroiDTO heroiParser(Ator ator){
        try{
            ator.getHeroi().getId();
        }catch(Exception e){
            return new HeroiDTO(null, null);
        }
       return new HeroiDTO(ator.getHeroi().getId(), ator.getHeroi().getNome());
    }

    public static Ator parseAtor(AtorNewDTO novoAtor){
        return new Ator(null, novoAtor.getNome(), novoAtor.getDataDeNascimento());
    }

}
