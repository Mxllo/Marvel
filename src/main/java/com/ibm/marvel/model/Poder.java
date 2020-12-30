package com.ibm.marvel.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Poder implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    @ManyToOne
    @JoinColumn(name="criador_id")
    private Criador criador;
    @ManyToMany(mappedBy = "poderes")
    private List<Heroi> herois = new ArrayList<>();

    public Poder(Integer id, String nome, Criador criador) {
        this.id = id;
        this.nome = nome;
        this.criador = criador;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Poder poder = (Poder) o;
        return id.equals(poder.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}