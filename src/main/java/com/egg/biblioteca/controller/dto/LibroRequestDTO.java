package com.egg.biblioteca.controller.dto;

public record LibroRequestDTO(
        Long isbn,
        String titulo,
        Integer ejemplares,
        String autorId,
        String editorialId
) {
}
