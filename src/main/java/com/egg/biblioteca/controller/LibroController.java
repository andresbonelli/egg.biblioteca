package com.egg.biblioteca.controller;

import com.egg.biblioteca.domain.entity.Libro;
import com.egg.biblioteca.service.LibroService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/libro")
@AllArgsConstructor
public class LibroController {

    private final LibroService libroService;

    @GetMapping("/listar")
    public List<Libro> listarLibros(){
        return libroService.listarLibros();
    }
}
