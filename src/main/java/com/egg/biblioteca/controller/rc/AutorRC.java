package com.egg.biblioteca.controller.rc;

import com.egg.biblioteca.domain.entity.Autor;
import com.egg.biblioteca.service.AutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autor")
@RequiredArgsConstructor
public class AutorRC {

    private final AutorService autorService;

    @GetMapping("/listar")
    public List<Autor> listarAutores(){
        return autorService.listarAutores();
    }

    @GetMapping("/buscar")
    public List<Autor> buscarPorNombre(@RequestParam("nombre") String nombre){
        return autorService.buscarPorNombre(nombre);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void crearAutor(@RequestParam String nombre){
        autorService.crearAutor(nombre);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void modificarAutor(@RequestBody Autor autor){
        autorService.modificarAutor(autor);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarAutor(@RequestBody Autor autor){
        autorService.eliminarAutor(autor);
    }

}
