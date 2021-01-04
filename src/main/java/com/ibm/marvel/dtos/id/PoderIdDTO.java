package com.ibm.marvel.dtos.id;

import com.ibm.marvel.dtos.CriadorDTO;
import com.ibm.marvel.dtos.HeroiDTO;
import com.ibm.marvel.model.Poder;
import lombok.Getter;
import lombok.Setter;


import java.util.HashSet;
import java.util.Set;

import static com.ibm.marvel.parser.PoderParser.*;

@Getter
@Setter
public class PoderIdDTO {

    private Integer id;
    private String nome;
    private CriadorDTO criador;
    private Set<HeroiDTO> herois = new HashSet<>();


    public PoderIdDTO(Poder poder) {
        this.id = poder.getId();
        this.nome = poder.getNome();
        this.criador = parseCriador(poder);
        this.herois = parseHeroi(poder);
    }
}
