package com.ibm.marvel.dtos.insert;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PoderNewDTO {

    private Integer id;
    private String nome;
    private String criador;

    public PoderNewDTO(String nome, String criador) {
        this.nome = nome;
        this.criador = criador;
    }
}
