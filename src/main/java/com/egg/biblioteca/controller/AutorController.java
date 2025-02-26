package com.egg.biblioteca.controller;

import com.egg.biblioteca.domain.entity.Autor;
import com.egg.biblioteca.service.AutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/autor")
@RequiredArgsConstructor
public class AutorController {

    private final AutorService autorService;

    @GetMapping("/listar")
    public List<Autor> listarAutores(){
        return autorService.listarAutores();
    }

    @PostMapping
    public void crearAutor(@RequestBody Map<String, String> request){
        String nombre = request.get("nombre");
        autorService.crearAutor(nombre);
    }

}
