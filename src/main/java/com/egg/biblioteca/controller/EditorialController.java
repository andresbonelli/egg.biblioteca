package com.egg.biblioteca.controller;

import com.egg.biblioteca.service.EditorialService;
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
@RequestMapping("/editorial")
@RequiredArgsConstructor
public class EditorialController {

    private final EditorialService editorialService;

    @GetMapping("/registrar")
    public String registrar(){
        return "editorial_form";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam("nombre") String nombre, ModelMap model){
        editorialService.crearEditorial(nombre);
        model.put("exito", "Editorial registrada con éxito!");
        return "index";
    }

    @GetMapping("/lista")
    public String listar(Model model){
        model.addAttribute("editoriales", editorialService.listarEditoriales());
        return "editorial_list";
    }
}
