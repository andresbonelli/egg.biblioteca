package com.egg.biblioteca.controller.rc;

import com.egg.biblioteca.domain.entity.Editorial;
import com.egg.biblioteca.service.EditorialService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rc/editorial")
@RequiredArgsConstructor
public class EditorialRC {

    private final EditorialService editorialService;

    @GetMapping("/listar")
    public List<Editorial> listarEditoriales(){
        return editorialService.listarEditoriales();
    }

    @GetMapping("/buscar")
    public List<Editorial> buscarPorNombre(@RequestParam("nombre") String nombre){
        return editorialService.buscarPorNombre(nombre);
    }

    @PostMapping
    public Editorial crearEditorial(@RequestParam String nombre){
        return editorialService.crearEditorial(nombre);
    }

    @PutMapping
    public ResponseEntity<Editorial> modificarEditorial(@RequestBody Editorial editorial){
        try {
            return ResponseEntity.ok(editorialService.modificarEditorial(editorial));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    public void eliminarEditorial(@RequestBody Editorial editorial){
        editorialService.eliminarEditorial(editorial);
    }
}
