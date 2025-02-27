package com.egg.biblioteca.controller;

import com.egg.biblioteca.service.AutorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequestMapping("/autor")
@RequiredArgsConstructor
public class AutorController {

    private final AutorService autorService;

    @GetMapping("/registrar") // localhost:8080/autor/registrar
    public String registrar() {
        return "autor_form.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombre){
        try {
            autorService.crearAutor(nombre);
        } catch (Exception ex) {
            log.error("Error al crear el autor {}", ex.getMessage(), ex);
            return "autor_form.html";
        }
        return "index.html";
    }
}

