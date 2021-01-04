package com.ibm.marvel.dtos.insert;

import com.ibm.marvel.dtos.AtorDTO;
import com.ibm.marvel.dtos.CriadorDTO;
import com.ibm.marvel.dtos.MidiaDTO;
import com.ibm.marvel.dtos.PoderDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class HeroiNewDTO {

    private Integer id;
    private String nome;
    private String origem;
    private String ator;
    private String criador;
    private Set<String> poderes;

    public HeroiNewDTO(Integer id, String nome, String origem, String ator,
                       String criador, Set<String> poderes) {
        this.id = id;
        this.nome = nome;
        this.origem = origem;
        this.ator = ator;
        this.criador = criador;
        this.poderes = poderes;
    }
}
