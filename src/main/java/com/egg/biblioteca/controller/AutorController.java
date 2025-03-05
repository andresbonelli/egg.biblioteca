package com.egg.biblioteca.controller;

import com.egg.biblioteca.service.AutorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequestMapping("/autor")
@RequiredArgsConstructor
public class AutorController {

    private final AutorService autorService;

    @GetMapping("/registrar") // localhost:8080/autor/registrar
    public String registrar() {
        return "autor_form.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombre, ModelMap model){
        autorService.crearAutor(nombre);
        model.put("exito", "Autor registrado con éxito!");
        return "index.html";
    }

    @GetMapping("/lista")
    public String listar(Model model) {
        model.addAttribute("autores", autorService.listarAutores());
        return "autor_list.html";
    }
}

