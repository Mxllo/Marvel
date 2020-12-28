package com.ibm.marvel.model;

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