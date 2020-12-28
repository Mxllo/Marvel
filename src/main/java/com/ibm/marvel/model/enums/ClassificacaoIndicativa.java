package com.ibm.marvel.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ClassificacaoIndicativa {
    L(1, "Livre"),
    DEZANOS(10, "+ 10 Anos"),
    DOZEANOS(12, "+ 12 Anos"),
    QUATORZEANOS(14, "+ 14 Anos"),
    DEZESSEISANOS(16, "+ 16 Anos"),
    DEZOITOANOS(18, "+ 18 Anos");

    private int codigo;
    private String descricao;

    public static ClassificacaoIndicativa toEnum(Integer codigo) {
        if(codigo == null)
            return null;

        for (ClassificacaoIndicativa classificacao : ClassificacaoIndicativa.values()) {
            if (codigo.equals(classificacao.getCodigo()))
                return classificacao;
        }
        throw new IllegalStateException("Id inválido: " + codigo);
    }


}
