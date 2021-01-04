package com.ibm.marvel.services.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DataViolationException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DataViolationException(String s) {
        super(s);
    }


}
