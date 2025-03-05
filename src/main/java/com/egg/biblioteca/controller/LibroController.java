package com.egg.biblioteca.controller;

import com.egg.biblioteca.controller.dto.LibroRequestDTO;
import com.egg.biblioteca.domain.entity.Libro;
import com.egg.biblioteca.service.AutorService;
import com.egg.biblioteca.service.EditorialService;
import com.egg.biblioteca.service.LibroService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Slf4j
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
        //model.addAttribute("libro", new Libro());
        model.addAttribute("autores", autorService.listarAutores());
        model.addAttribute("editoriales", editorialService.listarEditoriales());
        return "libro_form.html";
    }

    @PostMapping("/registro")
    public String registro(@ModelAttribute LibroRequestDTO libro, ModelMap model) {
        try {
            libroService.crearLibro(libro);
            model.put("exito", "Libro registrado con éxito!");
        } catch (Exception e) {
            log.error("Error al crear el libro {}", e.getMessage(), e);
            model.put("error", e.getMessage());
            model.addAttribute("autores", autorService.listarAutores());
            model.addAttribute("editoriales", editorialService.listarEditoriales());
            return "libro_form.html";
        }
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
        try {
            libroService.modificarLibro(libro);
            model.addAttribute("exito", "Libro modificado con éxito!");
            return "redirect:lista";
        } catch (Exception e) {
            log.error("Error al modificar el libro {}", e.getMessage(), e);
            model.addAttribute("error", e.getMessage());
            model.addAttribute("libro", libro);
            model.addAttribute("autores", autorService.listarAutores());
            model.addAttribute("editoriales", editorialService.listarEditoriales());
            return "libro_edit_form.html";
        }
    }

    @GetMapping("/eliminar/{isbn}")
    public String eliminar(@PathVariable Long isbn, ModelMap model) {
        try {
            libroService.eliminarLibro(isbn);
            model.addAttribute("exito", "Libro eliminado con éxito!");
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "redirect:../lista";
    }
}
