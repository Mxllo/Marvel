package com.ibm.marvel.model;

import java.io.Serializable;

import lombok.*;

import javax.persistence.*;

import java.util.*;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
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
    private Set<Poder> poderes = new HashSet<>();
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true) @JoinColumn(name = "ator_id")
    private Ator ator;
    @ManyToOne @JoinColumn(name="criador_id") 
    private Criador criador;
    @ManyToMany(mappedBy = "herois")
    private Set<Midia> midias = new HashSet<>();

    public Heroi(Integer id, String nome, String origem, Ator ator, Criador criador) {
        this.id = id;
        this.nome = nome;
        this.origem = origem;
        this.ator = ator;
        this.criador = criador;
    }

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
