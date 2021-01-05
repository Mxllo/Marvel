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
        Criador c2 = new Criador(null,"Stan Lee");
        Criador c3 = new Criador(null,"Michael Rogers");
        Criador c4 = new Criador(null,"Felipe Neto");
        Criador c5 = new Criador(null,"Bruno Linhares");
        criadorRepository.save(c1);
        criadorRepository.save(c2);
        criadorRepository.save(c3);
        criadorRepository.save(c4);
        criadorRepository.save(c5);


        Poder p1 = new Poder(null,"Agilidade",c1);
        Poder p2 = new Poder(null,"Voo",c1);
        Poder p3 = new Poder(null,"Telecinese",c1);
        Poder p4 = new Poder(null,"Forca",c2);
        Poder p5 = new Poder(null,"Mira",c2);
        Poder p6 = new Poder(null,"Inteligencia",c3);
        Poder p7 = new Poder(null,"Magia",c4);
        Poder p8 = new Poder(null,"Manipulacao de energia",c4);
        Poder p9 = new Poder(null,"Velocidade",c4);
        poderRepository.save(p1);
        poderRepository.save(p2);
        poderRepository.save(p3);
        poderRepository.save(p4);
        poderRepository.save(p5);
        poderRepository.save(p6);
        poderRepository.save(p7);
        poderRepository.save(p8);
        poderRepository.save(p9);

        Ator a1 = new Ator(null,"Brie Larson", s.parse("15/03/1980"));
        Ator a2 = new Ator(null,"Chris Evans", s.parse("15/08/1967"));
        Ator a3 = new Ator(null,"Mark Ruffalo", s.parse("13/06/1981"));
        Ator a4 = new Ator(null,"Chadwick Boseman", s.parse("29/09/1976"));
        Ator a5 = new Ator(null,"Chris Pratt", s.parse("21/07/1979"));
        Ator a6 = new Ator(null,"Samuel L Jackson", s.parse("21/12/1948"));
        Ator a7 = new Ator(null,"Benedict Cumberbatch", s.parse("19/06/1976"));
        atorRepository.save(a1);
        atorRepository.save(a2);
        atorRepository.save(a3);
        atorRepository.save(a4);
        atorRepository.save(a5);
        atorRepository.save(a6);
        atorRepository.save(a7);

        Heroi h1 = new Heroi(null,"Capitao America", "EUA",Set.of(p1,p4,p9),a2,c1);
        Heroi h2 = new Heroi(null,"Capita Marvel", "Mar'vel",Set.of(p2,p3,p4,p8,p9),a1,c2);
        Heroi h3 = new Heroi(null,"Hulk", "EUA",Set.of(p4,p9),a3,c2);
        Heroi h4 = new Heroi(null,"Pantera Negra", "Wakanda",Set.of(p1,p5,p6,p9),a4,c3);
        Heroi h5 = new Heroi(null,"Peter Quill", "Ego",Set.of(p1,p5,p6),a5,c3);
        Heroi h6 = new Heroi(null,"Nick Fury", "EUA",Set.of(p6,p5),a6,c3);
        Heroi h7 = new Heroi(null,"Doutor Estranho", "EUA",Set.of(p7,p2,p8,p6),a7,c5);

        heroiRepository.save(h1);
        heroiRepository.save(h2);
        heroiRepository.save(h3);
        heroiRepository.save(h4);
        heroiRepository.save(h5);
        heroiRepository.save(h6);
        heroiRepository.save(h7);


        Filme f1 = new Filme(null,c1,"Vingadores", ClassificacaoIndicativa.DEZESSEISANOS,
                Set.of(h1,h3,h6), LocalTime.of(1,30));
        Filme f2 = new Filme(null,c2,"Doutor Estranho", ClassificacaoIndicativa.DEZOITOANOS,
                Set.of(h7,h6), LocalTime.of(2,10));
        Filme f3 = new Filme(null,c4,"Guardiões da Galaxia", ClassificacaoIndicativa.DOZEANOS,
                Set.of(h5), LocalTime.of(2,30));
        Filme f4 = new Filme(null,c5,"Vingadores 2", ClassificacaoIndicativa.DEZANOS,
                Set.of(h1,h2,h3,h4,h7), LocalTime.of(2,10));
        Filme f5 = new Filme(null,c5,"Guerra Civil", ClassificacaoIndicativa.L,
                Set.of(h1,h3,h6), LocalTime.of(1,40));
        Filme f6 = new Filme(null,c5,"Pantera Negra", ClassificacaoIndicativa.DEZOITOANOS,
                Set.of(h4), LocalTime.of(1,55));
        Revista r1 = new Revista(null, c3, "Guerras Secretas", ClassificacaoIndicativa.DEZOITOANOS,
                Set.of(h2,h5,h6,h7), 300);
        Revista r2 = new Revista(null, c4, "Vingadores: Breakdown", ClassificacaoIndicativa.DEZESSEISANOS,
                Set.of(h1,h3,h4,h6,h7), 210);
        Revista r3 = new Revista(null, c5, "Fury", ClassificacaoIndicativa.DEZESSEISANOS,
                Set.of(h6,h3), 120);

        midiaRepository.save(f1);
        midiaRepository.save(f2);
        midiaRepository.save(f3);
        midiaRepository.save(f4);
        midiaRepository.save(f5);
        midiaRepository.save(f6);
        midiaRepository.save(r1);
        midiaRepository.save(r2);
        midiaRepository.save(r3);
    }
}
