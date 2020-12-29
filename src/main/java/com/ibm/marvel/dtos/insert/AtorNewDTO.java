package com.ibm.marvel.dtos.insert;

import com.ibm.marvel.model.Ator;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
public class AtorNewDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    private Integer id;
    //@NotEmpty(message="Preenchimento obrigatório")
    private String nome;
    //@NotEmpty(message="Preenchimento obrigatório")
    private Date dataDeNascimento;

    public AtorNewDTO(Ator ator) {
        this.id = ator.getId();
        this.nome = ator.getNome();
        this.dataDeNascimento = ator.getDataDeNascimento();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(Date dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }
}
