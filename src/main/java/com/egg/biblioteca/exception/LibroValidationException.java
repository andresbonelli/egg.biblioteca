package com.egg.biblioteca.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class LibroValidationException extends RuntimeException {
    public LibroValidationException(String message) {
        super(message);
    }
}
