package com.egg.biblioteca.controller.rc;

import com.egg.biblioteca.domain.entity.Autor;
import com.egg.biblioteca.service.AutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/autor")
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
    public Autor crearAutor(@RequestParam String nombre){
        return autorService.crearAutor(nombre);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Autor> modificarAutor(@RequestBody Autor autor){
        try {
            return ResponseEntity.ok(autorService.modificarAutor(autor));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarAutor(@RequestBody Autor autor){
        autorService.eliminarAutor(autor);
    }

}
