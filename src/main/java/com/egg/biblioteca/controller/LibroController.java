package com.egg.biblioteca.controller;

import com.egg.biblioteca.controller.dto.LibroRequestDTO;
import com.egg.biblioteca.domain.entity.Libro;
import com.egg.biblioteca.service.AutorService;
import com.egg.biblioteca.service.EditorialService;
import com.egg.biblioteca.service.LibroService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/libro")
@RequiredArgsConstructor
public class LibroController {
    
    private final LibroService libroService;
    private final AutorService autorService;
    private final EditorialService editorialService;

    @GetMapping("/lista")
    public String listar(ModelMap model) {
        model.addAttribute("libros", libroService.listarLibros());
        return "libro_list.html";
    }

    @GetMapping("/registrar")
    public String registrar(ModelMap model) {
        model.addAttribute("autores", autorService.listarAutores());
        model.addAttribute("editoriales", editorialService.listarEditoriales());
        return "libro_form.html";
    }

    @PostMapping("/registro")
    public String registro(@ModelAttribute LibroRequestDTO libro, ModelMap model) {
        libroService.crearLibro(libro);
        model.put("exito", "Libro registrado con éxito!");
        return "index.html";
    }

    @GetMapping("/editar/{isbn}")
    public String editar(@PathVariable Long isbn, ModelMap model) {
        Libro libro = libroService.buscarPorIsbn(isbn);
        LibroRequestDTO libroRequestDTO = new LibroRequestDTO(
                libro.getIsbn(),
                libro.getTitulo(),
                libro.getEjemplares(),
                libro.getAutor().getId().toString(),
                libro.getEditorial().getId().toString()
        );
        model.addAttribute("libro", libroRequestDTO);
        model.addAttribute("autores", autorService.listarAutores());
        model.addAttribute("editoriales", editorialService.listarEditoriales());
        return "libro_edit_form.html";
    }

    @PostMapping("/editar")
    public String editar(@ModelAttribute LibroRequestDTO libro, ModelMap model) {
        libroService.modificarLibro(libro);
        model.addAttribute("exito", "Libro modificado con éxito!");
        return "redirect:lista";
    }

    @GetMapping("/eliminar/{isbn}")
    public String eliminar(@PathVariable Long isbn, ModelMap model) {
        libroService.eliminarLibro(isbn);
        model.addAttribute("exito", "Libro eliminado con éxito!");
        return "redirect:../lista";
    }
}
