package com.egg.biblioteca.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RegistroRelacionadoException extends RuntimeException {
    public RegistroRelacionadoException(String message) {
        super(message);
    }
}
