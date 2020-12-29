package com.ibm.marvel.dtos.insert;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class PoderNewDTO {

    private Integer id;
    @NotEmpty(message="Preenchimento obrigatório")
    private String nome;
    @NotEmpty(message="Preenchimento obrigatório")
    private String criador;

    public PoderNewDTO(String nome, String criador) {
        this.nome = nome;
        this.criador = criador;
    }
}
