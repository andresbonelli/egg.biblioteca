package com.egg.biblioteca.controller;

import com.egg.biblioteca.domain.entity.Editorial;
import com.egg.biblioteca.service.EditorialService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/editorial")
@RequiredArgsConstructor
public class EditorialController {

    private final EditorialService editorialService;

    @GetMapping("/listar")
    public String listarEditoriales(){
        return editorialService.listarEditoriales().toString();
    }

    @GetMapping("/buscar")
    public List<Editorial> buscarPorNombre(@RequestParam("nombre") String nombre){
        return editorialService.buscarPorNombre(nombre);
    }

    @PostMapping
    public void crearEditorial(@RequestBody Map<String, String> request){
        String nombre = request.get("nombre");
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
