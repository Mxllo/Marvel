package com.ibm.marvel.services.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ObjectDuplicationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ObjectDuplicationException(String message) {
        super(message);
    }

    public ObjectDuplicationException(String message, Throwable cause) {
        super(message, cause);
    }
}
