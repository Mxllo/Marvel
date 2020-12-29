package com.ibm.marvel.dtos.id;

import com.ibm.marvel.dtos.HeroiDTO;
import com.ibm.marvel.dtos.MidiaDTO;
import com.ibm.marvel.dtos.PoderDTO;
import com.ibm.marvel.model.Criador;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import static com.ibm.marvel.services.parser.CriadorParser.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CriadorIdDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nome;
    private Set<HeroiDTO> herois = new HashSet<>();
    private Set<PoderDTO> poderes = new HashSet<>();
    private Set<MidiaDTO> midias = new HashSet<>();

    public CriadorIdDTO(Criador criador) {
        this.id = criador.getId();
        this.nome = criador.getNome();
        this.herois = parseHeroi(criador);
        this.poderes = parsePoder(criador);
        this.midias = parseMidia(criador);
    }
}
