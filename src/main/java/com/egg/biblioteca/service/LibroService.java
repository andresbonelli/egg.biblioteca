package com.egg.biblioteca.service;

import com.egg.biblioteca.domain.entity.Libro;
import com.egg.biblioteca.domain.repository.LibroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LibroService {

    private final LibroRepository libroRepository;

    public List<Libro> listarLibros() {
        return libroRepository.findAll();
    }
}
