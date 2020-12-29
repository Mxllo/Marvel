package com.ibm.marvel.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ibm.marvel.model.Criador;
import com.ibm.marvel.model.Heroi;
import com.ibm.marvel.model.Poder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PoderDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nome;

    public PoderDTO(Poder poder) {
        this.id = poder.getId();
        this.nome = poder.getNome();
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
