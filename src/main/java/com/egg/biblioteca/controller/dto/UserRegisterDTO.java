package com.egg.biblioteca.controller.dto;

public record UserRegisterDTO(
        String nombre,
        String email,
        String password,
        String confirmPassword
) {
}
