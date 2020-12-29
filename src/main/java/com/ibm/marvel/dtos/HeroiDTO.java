package com.ibm.marvel.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ibm.marvel.model.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
public class HeroiDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nome;

    public HeroiDTO(Heroi heroi) {
        this.id = heroi.getId();
        this.nome = heroi.getNome();
    }
}
