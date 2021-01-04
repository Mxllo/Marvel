package com.ibm.marvel.dtos.insert;

import com.ibm.marvel.model.Criador;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter


@NoArgsConstructor
public class CriadorNewDTO implements Serializable {

        private static final long serialVersionUID = 1L;

        private Integer id;
        private String nome;

        public CriadorNewDTO(Integer id, String nome) {
            this.id = id;
            this.nome = nome;
        }
}
