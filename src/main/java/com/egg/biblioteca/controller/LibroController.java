package com.egg.biblioteca.controller;

import com.egg.biblioteca.controller.dto.LibroRequestDTO;
import com.egg.biblioteca.domain.entity.Libro;
import com.egg.biblioteca.service.LibroService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{isbn}")
    public Libro buscarPorIsbn(@PathVariable("isbn") Long isbn){
        return libroService.buscarPorIsbn(isbn);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void crearLibro(@RequestBody LibroRequestDTO request){
        libroService.crearLibro(
                request.isbn(),
                request.titulo(),
                request.ejemplares(),
                request.autor(),
                request.editorial()
        );
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void modificarLibro(@RequestBody LibroRequestDTO request){
        libroService.modificarLibro(
                request.isbn(),
                request.titulo(),
                request.ejemplares(),
                request.autor(),
                request.editorial()
        );
    }

    @DeleteMapping("/{isbn}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarLibro(@PathVariable("isbn") Long isbn){
        libroService.eliminarLibro(isbn);
    }
}
