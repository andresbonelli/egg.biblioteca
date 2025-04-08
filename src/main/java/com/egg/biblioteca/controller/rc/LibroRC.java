package com.egg.biblioteca.controller.rc;

import com.egg.biblioteca.controller.dto.LibroRequestDTO;
import com.egg.biblioteca.controller.dto.LibroResponseDTO;
import com.egg.biblioteca.domain.entity.Libro;
import com.egg.biblioteca.service.LibroService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libro")
@AllArgsConstructor
public class LibroRC {

    private final LibroService libroService;

    @GetMapping("/listar")
    public List<LibroResponseDTO> listarLibros(){
        return libroService.listarLibros();
    }

    @GetMapping("/{isbn}")
    public Libro buscarPorIsbn(@PathVariable("isbn") Long isbn){
        return libroService.buscarPorIsbn(isbn);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LibroResponseDTO crearLibro(@RequestBody LibroRequestDTO request){
        return libroService.crearLibro(request);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<LibroResponseDTO> modificarLibro(@RequestBody LibroRequestDTO request){
        try {
            return ResponseEntity.ok(libroService.modificarLibro(request));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{isbn}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarLibro(@PathVariable("isbn") Long isbn){
        libroService.eliminarLibro(isbn);
    }
}
