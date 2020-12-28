package com.ibm.marvel.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

public class Heroi implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nome")
    private String nome;
    @Column(name="origem")
    private String origem;

    @ManyToMany @JoinTable(name="HEROI_PODER", joinColumns = @JoinColumn(name ="heroi_id"),
            inverseJoinColumns = @JoinColumn(name ="poder_id"))
    private List<Poder> poderes = new ArrayList<>();

    @OneToOne @JoinColumn(name = "ator_id")
    @JsonIgnore
    private Ator ator;
    @ManyToOne @JoinColumn(name="criador_id")
    private Criador criador;

    @ManyToMany @JoinTable(name="HEROI_MIDIA", joinColumns = @JoinColumn(name ="heroi_id"),
            inverseJoinColumns = @JoinColumn(name ="midia_id"))
    private List<Midia> midias = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Heroi heroi = (Heroi) o;
        return id.equals(heroi.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
