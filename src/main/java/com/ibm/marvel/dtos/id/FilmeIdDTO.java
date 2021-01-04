package com.ibm.marvel.dtos.id;

import com.ibm.marvel.model.Midia;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
@Getter
@Setter
public class FilmeIdDTO  extends MidiaIdDTO{

    private LocalTime duracao;

    public FilmeIdDTO(Midia midia) {
        super(midia);
        this.duracao = midia.getDuracao();
    }
}
