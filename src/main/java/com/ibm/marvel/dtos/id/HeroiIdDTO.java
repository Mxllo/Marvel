package com.ibm.marvel.dtos.id;

import com.ibm.marvel.dtos.AtorDTO;
import com.ibm.marvel.dtos.CriadorDTO;
import com.ibm.marvel.dtos.MidiaDTO;
import com.ibm.marvel.dtos.PoderDTO;
import com.ibm.marvel.model.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import static com.ibm.marvel.parser.HeroiParser.*;

@Setter
@Getter
public class HeroiIdDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nome;
    private String origem;
    private AtorDTO ator;
    private CriadorDTO criador;
    private Set<PoderDTO> poderes = new HashSet<>();
    private Set<MidiaDTO> midias = new HashSet<>();

    public HeroiIdDTO(Heroi heroi) {
        this.id = heroi.getId();
        this.nome = heroi.getNome();
        this.origem = heroi.getOrigem();
        this.poderes = parsePoder(heroi);
        this.ator = parseAtor(heroi);
        this.criador = parseCriador(heroi);
        this.midias = parseMidia(heroi);
    }
}
