package com.ibm.marvel.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Criador implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    @OneToMany(mappedBy="criador")
    private List<Heroi> herois = new ArrayList<>();
    @OneToMany(mappedBy="criador")
    private List<Poder> poderes = new ArrayList<>();
    @OneToMany(mappedBy="criador")
    private List<Midia> midias = new ArrayList<>();

    public Criador(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Criador criador = (Criador) o;
        return id.equals(criador.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
