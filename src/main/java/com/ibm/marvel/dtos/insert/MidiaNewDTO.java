package com.ibm.marvel.dtos.insert;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;


import java.util.Set;


@Getter
@Setter
public class MidiaNewDTO {

    private Integer id;
    private String nome;
    private String criador;
    private String classificacao;
    private Set<String> herois;
    @JsonFormat(pattern = "hh:MM")
    private String duracao;
    private Integer paginas;

    public MidiaNewDTO(Integer id, String criador, String nome, String classificacao, Set<String> herois) {
        this.id = id;
        this.criador = criador;
        this.nome = nome;
        this.classificacao = classificacao;
        this.herois = herois;
    }
}
