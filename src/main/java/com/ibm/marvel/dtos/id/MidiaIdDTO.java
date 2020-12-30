package com.ibm.marvel.dtos.id;

import com.ibm.marvel.dtos.CriadorDTO;
import com.ibm.marvel.dtos.HeroiDTO;
import com.ibm.marvel.model.Criador;
import com.ibm.marvel.model.Heroi;
import com.ibm.marvel.model.Midia;
import com.ibm.marvel.model.enums.ClassificacaoIndicativa;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.ibm.marvel.services.parser.MidiaParser.parseCriador;
import static com.ibm.marvel.services.parser.MidiaParser.parseHeroi;

@Getter
@Setter
public class MidiaIdDTO implements Serializable {
    private static final long serialVersionUID = 1L;

        private Integer id;
        private CriadorDTO criador;
        private String nome;
        private ClassificacaoIndicativa classificacao;
        private Set<HeroiDTO> herois;

    public MidiaIdDTO(Midia midia) {
        this.id = midia.getId();
        this.criador = parseCriador(midia);
        this.nome = midia.getNome();
        this.classificacao = midia.getClassificacao();
        this.herois = parseHeroi(midia);
    }
}
