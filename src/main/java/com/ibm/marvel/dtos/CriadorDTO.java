package com.ibm.marvel.dtos;

import com.ibm.marvel.model.Criador;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
public class CriadorDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nome;

    public CriadorDTO(Criador Criador) {
        this.id = Criador.getId();
        this.nome = Criador.getNome();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
