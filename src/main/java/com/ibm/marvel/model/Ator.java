package com.ibm.marvel.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class Ator implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="nome")
    private String nome;
    @JsonFormat(pattern = "dd/MM/yyyy") @Column(name="dataDeNascimento")
    private Date dataDeNascimento;
    @OneToOne(cascade= CascadeType.ALL, mappedBy = "ator")
    private Heroi heroi;

    public Ator(Integer id, String nome, Date dataDeNascimento) {
        this.id = id;
        this.nome = nome;
        this.dataDeNascimento = dataDeNascimento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ator ator = (Ator) o;
        return id.equals(ator.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
