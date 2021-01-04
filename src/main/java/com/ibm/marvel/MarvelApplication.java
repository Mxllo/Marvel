package com.ibm.marvel;

import com.ibm.marvel.model.*;
import com.ibm.marvel.model.enums.ClassificacaoIndicativa;
import com.ibm.marvel.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class MarvelApplication implements CommandLineRunner {

    @Autowired
    private AtorRepository atorRepository;
    @Autowired
    private CriadorRepository criadorRepository;
    @Autowired
    private FilmeRepository filmeRepository;
    @Autowired
    private HeroiRepository heroiRepository;
    @Autowired
    private MidiaRepository midiaRepository;
    @Autowired
    private PoderRepository poderRepository;


    public static void main(String[] args) {
        SpringApplication.run(MarvelApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
        Criador c1 = new Criador(null,"Jhonny Silverhand");
        criadorRepository.save(c1);

        Ator a1 = new Ator(null,"Margot Robbie", s.parse("15/03/1978"));
        Heroi h1 = new Heroi(null,"Blackwidow", "Inglaterra",a1,c1);
        a1.setHeroi(h1);
        Poder p1 = new Poder(null,"Agilidade",c1);
        Filme f1 = new Filme(null,c1,"BlackMouth un", ClassificacaoIndicativa.DEZOITOANOS,
                Set.of(h1), LocalTime.of(1,10));;
        
        c1.getHerois().add(h1);
        c1.getMidias().add(f1);
        p1.getHerois().add(h1);
        poderRepository.save(p1);
        h1.getPoderes().add(p1);
        atorRepository.save(a1);
        heroiRepository.save(h1);
        poderRepository.save(p1);
        midiaRepository.save(f1);
    }
}
