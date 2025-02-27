package com.egg.biblioteca.controller;

import com.egg.biblioteca.service.EditorialService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequestMapping("/editorial")
@RequiredArgsConstructor
public class EditorialController {

    private final EditorialService editorialService;

    @GetMapping("/registrar")
    public String registrar(){
        return "editorial_form";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam("nombre") String nombre){
        try {
            editorialService.crearEditorial(nombre);
        } catch (Exception ex) {
            log.error("Error al crear la editorial {}", ex.getMessage(), ex);
            return "editorial_form";
        }
        return "index";
    }
}
