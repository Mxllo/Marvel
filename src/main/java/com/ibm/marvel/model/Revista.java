package com.ibm.marvel.model;

import com.ibm.marvel.model.enums.ClassificacaoIndicativa;
import lombok.*;

import javax.persistence.Entity;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Revista extends Midia{
    private Integer paginas;

    public Revista(Integer paginas) {
        this.paginas = paginas;
    }

    public Revista(Integer id, Criador criador, String nome, ClassificacaoIndicativa classificacao, List<Heroi> herois, Integer paginas) {
        super(id, criador, nome, classificacao, herois);
        this.paginas = paginas;
    }
}
