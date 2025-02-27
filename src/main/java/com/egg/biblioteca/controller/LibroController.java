package com.egg.biblioteca.controller;

import com.egg.biblioteca.controller.dto.LibroRequestDTO;
import com.egg.biblioteca.domain.entity.Libro;
import com.egg.biblioteca.service.AutorService;
import com.egg.biblioteca.service.EditorialService;
import com.egg.biblioteca.service.LibroService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
                           RedirectAttributes redirectAttrs) {
        try {
            libroService.crearLibro(new LibroRequestDTO(isbn, titulo, ejemplares, autorID, editorialID));
            redirectAttrs.addFlashAttribute("exito", "Libro registrado con éxito!");
        } catch (Exception e) {
            redirectAttrs.addFlashAttribute("error", "Error al registrar el libro.");
        }
        return "index.html";
    }
}
