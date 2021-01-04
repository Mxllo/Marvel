package com.ibm.marvel.services.helper;

import com.ibm.marvel.model.Criador;
import com.ibm.marvel.services.exception.DataViolationException;

import java.util.Optional;

public class CriadorHelper {

    public static void checkMaxCreations(Optional<Criador> obj) {
        if(obj.isPresent()) {
            Criador criador = obj.get();
            if ((criador.getMidias().size() + criador.getPoderes().size() + criador.getHerois().size()) >= 5)
                throw new DataViolationException("Criador possui 5 criações - Não é permitido mais criações!");
        }
    }

}
