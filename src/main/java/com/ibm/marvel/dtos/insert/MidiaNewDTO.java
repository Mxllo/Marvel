package com.ibm.marvel.dtos.insert;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;


@Getter
@Setter
public class MidiaNewDTO {

    private Integer id;
    private String nome;
    private String criador;
    private String classificacao;
    private List<String> herois;
    @JsonFormat(pattern = "hh:MM")
    private String duracao;
    private Integer paginas;

    public MidiaNewDTO(Integer id, String criador, String nome, String classificacao, List<String> herois) {
        this.id = id;
        this.criador = criador;
        this.nome = nome;
        this.classificacao = classificacao;
        this.herois = herois;
    }
}
