package com.egg.biblioteca.controller.rc;

import com.egg.biblioteca.domain.entity.Editorial;
import com.egg.biblioteca.service.EditorialService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/editorial")
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
    public void crearEditorial(@RequestParam String nombre){
        editorialService.crearEditorial(nombre);
    }

    @PutMapping
    public void modificarEditorial(@RequestBody Editorial editorial){
        editorialService.modificarEditorial(editorial);
    }

    @DeleteMapping
    public void eliminarEditorial(@RequestBody Editorial editorial){
        editorialService.eliminarEditorial(editorial);
    }
}
