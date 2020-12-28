package com.ibm.marvel.model;

import com.ibm.marvel.model.enums.ClassificacaoIndicativa;
import lombok.*;

import javax.persistence.Entity;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Filme extends Midia{
    private LocalTime duracao;


    public Filme(Integer id, Criador criador, String nome, ClassificacaoIndicativa classificacao, LocalTime duracao) {
        super(id, criador, nome, classificacao);
        this.duracao = duracao;
    }
}
