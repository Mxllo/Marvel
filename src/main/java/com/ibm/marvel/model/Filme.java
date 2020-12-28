package com.ibm.marvel.model;

import com.ibm.marvel.model.enums.ClassificacaoIndicativa;
import lombok.*;

import javax.persistence.Entity;
import java.sql.Time;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Filme extends Midia{
    private Time duracao;

    public Filme(Time duracao) {
        this.duracao = duracao;
    }

    public Filme(Integer id, Criador criador, String nome, ClassificacaoIndicativa classificacao, List<Heroi> herois, Time duracao) {
        super(id, criador, nome, classificacao, herois);
        this.duracao = duracao;
    }
}
