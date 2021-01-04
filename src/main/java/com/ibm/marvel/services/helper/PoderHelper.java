package com.ibm.marvel.services.helper;

import com.ibm.marvel.dtos.insert.PoderNewDTO;
import com.ibm.marvel.model.Poder;
import com.ibm.marvel.services.CriadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PoderHelper {

    @Autowired
    private CriadorService criadorService;

    public Poder parsePoder(PoderNewDTO novoPoder){
        return new Poder(null, novoPoder.getNome(), criadorService.findByNome(novoPoder.getCriador()));
    }

    public Poder parsePoderComId(PoderNewDTO novoPoder) {
        return new Poder(novoPoder.getId(), novoPoder.getNome(), criadorService.findByNome(novoPoder.getCriador()));
    }
}
