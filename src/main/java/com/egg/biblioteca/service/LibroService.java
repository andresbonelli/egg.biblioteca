package com.egg.biblioteca.service;

import com.egg.biblioteca.domain.entity.Libro;
import com.egg.biblioteca.domain.repository.LibroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LibroService {

    private final LibroRepository libroRepository;

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
