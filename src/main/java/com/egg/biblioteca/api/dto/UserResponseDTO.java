package com.egg.biblioteca.api.dto;

public record UserResponseDTO(
        String id,
        String nombre,
        String email,
        String rol,
        String imagenId
) {
}
