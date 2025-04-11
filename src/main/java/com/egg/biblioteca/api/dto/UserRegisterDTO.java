package com.egg.biblioteca.api.dto;

public record UserRegisterDTO(
        String nombre,
        String email,
        String password,
        String confirmPassword
) {
}
