package com.ibm.marvel.dtos.id;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ibm.marvel.dtos.HeroiDTO;
import com.ibm.marvel.model.Ator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static com.ibm.marvel.parser.AtorParser.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AtorIdDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nome;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataDeNascimento;
    private List<HeroiDTO> heroi;

    public AtorIdDTO(Ator ator) {
        this.id = ator.getId();
        this.nome = ator.getNome();
        this.heroi = Collections.singletonList(heroiParser(ator));
        this.dataDeNascimento = ator.getDataDeNascimento();
    }

}
