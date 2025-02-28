package com.egg.biblioteca.controller;

import com.egg.biblioteca.controller.dto.LibroRequestDTO;
import com.egg.biblioteca.service.AutorService;
import com.egg.biblioteca.service.EditorialService;
import com.egg.biblioteca.service.LibroService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/libro")
@RequiredArgsConstructor
public class LibroController {
    
    private final LibroService libroService;
    private final AutorService autorService;
    private final EditorialService editorialService;

    @GetMapping("/registrar")
    public String registrar(Model model) {
        //model.addAttribute("libro", new Libro());
        model.addAttribute("autores", autorService.listarAutores());
        model.addAttribute("editoriales", editorialService.listarEditoriales());
        return "libro_form.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam Long isbn,
                           @RequestParam String titulo,
                           @RequestParam Integer ejemplares,
                           @RequestParam String autorID,
                           @RequestParam String editorialID,
                           ModelMap model) {
        try {
            libroService.crearLibro(new LibroRequestDTO(isbn, titulo, ejemplares, autorID, editorialID));
            model.put("exito", "Libro registrado con éxito!");
        } catch (Exception e) {
            model.put("error", e.getMessage());
            model.addAttribute("autores", autorService.listarAutores());
            model.addAttribute("editoriales", editorialService.listarEditoriales());
            return "libro_form.html";
        }
        return "index.html";
    }
}
