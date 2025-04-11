package com.egg.biblioteca.api.dto;

public record LibroRequestDTO(
        Long isbn,
        String titulo,
        Integer ejemplares,
        String autorId,
        String editorialId
) {
}
