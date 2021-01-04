package com.ibm.marvel.services.helper;

import com.ibm.marvel.dtos.insert.MidiaNewDTO;
import com.ibm.marvel.model.Filme;
import com.ibm.marvel.model.Heroi;
import com.ibm.marvel.model.Midia;
import com.ibm.marvel.model.Revista;
import com.ibm.marvel.model.enums.ClassificacaoIndicativa;
import com.ibm.marvel.services.CriadorService;
import com.ibm.marvel.services.HeroiService;
import com.ibm.marvel.services.exception.DataViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import static com.ibm.marvel.parser.MidiaParser.horaParser;
import static com.ibm.marvel.parser.MidiaParser.minutoParser;

@Service
public class MidiaHelper implements Serializable {
    private static final long serialVersionUID = 1L;

    @Autowired
    private CriadorService criadorService;
    @Autowired
    private HeroiService heroiService;

    public Midia parseMidiaFilme(MidiaNewDTO novaMidia){
        return new Filme(null, criadorService.findByNome(novaMidia.getCriador()), novaMidia.getNome(),
                ClassificacaoIndicativa.valueOf(novaMidia.getClassificacao()), parseHeroi(novaMidia),
                LocalTime.of(horaParser(novaMidia.getDuracao()), minutoParser(novaMidia.getDuracao())));
    }

    public Midia parseMidiaRevista(MidiaNewDTO novaMidia){
        return new Revista(null, criadorService.findByNome(novaMidia.getCriador()), novaMidia.getNome(),
                ClassificacaoIndicativa.valueOf(novaMidia.getClassificacao()),parseHeroi(novaMidia), novaMidia.getPaginas());
    }

    public Midia parseMidiaFilmeComId(MidiaNewDTO novaMidia){
        return new Filme(novaMidia.getId(), criadorService.findByNome(novaMidia.getCriador()), novaMidia.getNome(),
                ClassificacaoIndicativa.valueOf(novaMidia.getClassificacao()), parseHeroi(novaMidia),
                LocalTime.of(horaParser(novaMidia.getDuracao()), minutoParser(novaMidia.getDuracao())));
    }

    public Midia parseMidiaRevistaComId(MidiaNewDTO novaMidia){
        return new Revista(novaMidia.getId(), criadorService.findByNome(novaMidia.getCriador()), novaMidia.getNome(),
                ClassificacaoIndicativa.valueOf(novaMidia.getClassificacao()),
                parseHeroi(novaMidia), novaMidia.getPaginas());
    }

    public Set<Heroi> parseHeroi(MidiaNewDTO novaMidia) {
        Set<Heroi> heroes = new HashSet<>();
        System.out.println(novaMidia.getHerois());
        novaMidia.getHerois().forEach((heroi) -> {
            heroes.add(heroiService.findByNome(heroi));
            if(heroes.size()>5)
                throw new DataViolationException("A mídia possui mais de 5 heróis");
        });
        return heroes;
    }

}
