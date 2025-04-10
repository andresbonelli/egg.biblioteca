package com.egg.biblioteca.api.dto;

import java.time.LocalDate;

public record LibroResponseDTO(
        Long isbn,
        String titulo,
        Integer ejemplares,
        LocalDate fechaAlta,
        String nombreAutor,
        String nombreEditorial
) {
}
