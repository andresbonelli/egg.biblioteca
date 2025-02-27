package com.egg.biblioteca.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RegistroNoExisteException extends RuntimeException {
    public RegistroNoExisteException() {
        super("El registro solicitado no existe.");
    }
}
