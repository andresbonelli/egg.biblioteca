package com.egg.biblioteca.service;

import com.egg.biblioteca.domain.entity.Libro;
import com.egg.biblioteca.domain.repository.AutorRepository;
import com.egg.biblioteca.domain.repository.EditorialRepository;
import com.egg.biblioteca.domain.repository.LibroRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LibroService {

    private final LibroRepository libroRepository;
    private final AutorService autorService;
    private final EditorialService editorialService;

    @Transactional
    public void crearLibro(Long isbn, String titulo, Integer ejemplares, String nombreAutor, String nombreEditorial) {
        Libro libro = new Libro();
        libro.setIsbn(isbn);
        libro.setTitulo(titulo);
        libro.setEjemplares(ejemplares);
        libro.setAlta(new java.util.Date());
        try {
            libro.setAutor(autorService.buscarPorNombre(nombreAutor).get(0));
            libro.setEditorial(editorialService.buscarPorNombre(nombreEditorial).get(0));
        } catch (IndexOutOfBoundsException e) {
            log.error("Error al crear el libro: Editorial o Autor no existente: {}", e.getMessage());
        }
        libroRepository.save(libro);
    }

    @Transactional(readOnly = true)
    public List<Libro> listarLibros() {
        return libroRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Libro> buscarPorTitulo(String titulo) {
        return libroRepository.listarPorTitulo(titulo);
    }

    @Transactional(readOnly = true)
    public List<Libro> buscarPorAutor(String nombre) {
        return libroRepository.listarPorAutor(nombre);
    }

    @Transactional(readOnly = true)
    public List<Libro> buscarPorEditorial(String nombre) {
        return libroRepository.listarPorEditorial(nombre);
    }
}
