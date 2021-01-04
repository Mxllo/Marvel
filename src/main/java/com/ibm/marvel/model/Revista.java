package com.ibm.marvel.model;

import com.ibm.marvel.model.enums.ClassificacaoIndicativa;
import lombok.*;

import javax.persistence.Entity;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Revista extends Midia{
    private Integer paginas;

    public Revista(Integer id, Criador criador, String nome, ClassificacaoIndicativa classificacao,
                   Set<Heroi> herois, Integer paginas) {
        super(id, criador, nome, classificacao, herois);
        this.paginas = paginas;
    }

    @Override
    public LocalTime getDuracao() {
        return null;
    }
}
