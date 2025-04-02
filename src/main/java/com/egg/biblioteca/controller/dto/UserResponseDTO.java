package com.egg.biblioteca.controller.dto;

public record UserResponseDTO(
        String id,
        String nombre,
        String email,
        String rol,
        String imagenId
) {
}
