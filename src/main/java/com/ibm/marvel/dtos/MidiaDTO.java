package com.ibm.marvel.dtos;

import com.ibm.marvel.model.Midia;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import static com.ibm.marvel.parser.MidiaParser.classDefiner;

@AllArgsConstructor
@Getter
@Setter
public class MidiaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nome;
    private String tipo;

    public MidiaDTO(Midia midia) {
        this.id = midia.getId();
        this.nome = midia.getNome();
        this.tipo = classDefiner(midia);
    }
}
