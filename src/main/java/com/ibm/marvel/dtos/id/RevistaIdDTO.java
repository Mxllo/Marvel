package com.ibm.marvel.dtos.id;

import com.ibm.marvel.model.Midia;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RevistaIdDTO extends MidiaIdDTO{
    
    private Integer paginas;

    public RevistaIdDTO(Midia midia) {
        super(midia);
        this.paginas = midia.getPaginas();
    }
}
