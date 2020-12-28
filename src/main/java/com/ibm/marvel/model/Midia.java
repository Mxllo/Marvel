package com.ibm.marvel.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ibm.marvel.model.enums.ClassificacaoIndicativa;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity

@Getter
@Setter
@NoArgsConstructor
public abstract class Midia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name="criador_id") @JsonIgnore
    private Criador criador;
    private String nome;
    private ClassificacaoIndicativa classificacao;
    @ManyToMany(mappedBy = "midias")
    private List<Heroi> herois = new ArrayList<>();

    public Midia(Integer id, Criador criador, String nome, ClassificacaoIndicativa classificacao) {
        this.id = id;
        this.criador = criador;
        this.nome = nome;
        this.classificacao = classificacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Midia midia = (Midia) o;
        return id.equals(midia.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
