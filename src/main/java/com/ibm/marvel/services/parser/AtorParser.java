package com.ibm.marvel.services.parser;

import com.ibm.marvel.dtos.HeroiDTO;
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

}
