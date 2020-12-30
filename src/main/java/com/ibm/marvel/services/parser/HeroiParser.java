package com.ibm.marvel.services.parser;

import com.ibm.marvel.dtos.*;
import com.ibm.marvel.model.Heroi;
import com.ibm.marvel.model.Midia;
import com.ibm.marvel.model.Poder;
import com.ibm.marvel.services.MidiaService;
import com.ibm.marvel.services.PoderService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import static com.ibm.marvel.services.parser.MidiaParser.classDefiner;

public class HeroiParser implements Serializable {
    private static final long serialVersionUID = 1L;


    public static Set<PoderDTO> parsePoder(Heroi heroi){
        Set<PoderDTO> poderes = new HashSet<>();
        heroi.getPoderes().forEach((poder) -> {
            poderes.add(new PoderDTO(poder.getId(), poder.getNome()) );
        });
        return poderes;
    }

    public static AtorDTO parseAtor(Heroi heroi){
        return new AtorDTO(heroi.getAtor().getId(), heroi.getAtor().getNome());
    }

    public static CriadorDTO parseCriador(Heroi heroi){
        return new CriadorDTO(heroi.getCriador().getId(), heroi.getCriador().getNome());
    }

    public static Set<MidiaDTO> parseMidia(Heroi heroi){
        Set<MidiaDTO> midias = new HashSet<>();
        heroi.getMidias().forEach((midia) -> {
            System.out.println(midia.getClass());
            System.out.println(classDefiner(midia.getClass()));
            midias.add(new MidiaDTO(midia.getId(), midia.getNome(), classDefiner(midia.getClass())));
        });
        return midias;
    }
}
