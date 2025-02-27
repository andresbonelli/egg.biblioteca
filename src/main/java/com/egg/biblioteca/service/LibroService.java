package com.egg.biblioteca.service;

import com.egg.biblioteca.controller.dto.LibroRequestDTO;
import com.egg.biblioteca.domain.entity.Libro;
import com.egg.biblioteca.domain.repository.LibroRepository;
import com.egg.biblioteca.exception.LibroValidationException;
import com.egg.biblioteca.exception.RegistroNoExisteException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class LibroService {

    private final LibroRepository libroRepository;
    private final AutorService autorService;
    private final EditorialService editorialService;

    @Transactional(readOnly = true)
    public List<Libro> listarLibros() {
        return libroRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Libro buscarPorIsbn(Long isbn) {
        return libroRepository.findById(isbn).orElseThrow(RegistroNoExisteException::new);
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

    @Transactional
    public void crearLibro(LibroRequestDTO libro) {
        validar(libro.isbn(), libro.titulo(), libro.ejemplares());
        if (libroRepository.existsById(libro.isbn())) {
            throw new LibroValidationException("El ISBN ya existe.");
        }
        Libro nuevoLibro = new Libro();
        nuevoLibro.setIsbn(libro.isbn());
        nuevoLibro.setTitulo(libro.titulo());
        nuevoLibro.setEjemplares(libro.ejemplares());
        try {
            nuevoLibro.setAutor(autorService.buscarPorId(UUID.fromString(libro.autorID())));
            nuevoLibro.setEditorial(editorialService.buscarPorId(UUID.fromString(libro.editorialID())));
        } catch (Exception e) {
            log.error("Error al crear el libro: {}", e.getMessage());
            throw new LibroValidationException("Editorial o Autor no existente.");
        }
        nuevoLibro.setAlta(new Date());
        libroRepository.save(nuevoLibro);
    }

    public void crearLibroDesdeController(Libro libro) {
    }


    @Transactional
    public void eliminarLibro(Long isbn) {
        Libro libro = libroRepository.findById(isbn).orElseThrow(RegistroNoExisteException::new);
        libroRepository.delete(libro);
    }

    private void validar (Long isbn, String titulo, Integer ejemplares) throws LibroValidationException{
        if (null == isbn){
            throw new LibroValidationException("El ISBN no puede ser NULO.");
        }
        if(null == titulo || titulo.isBlank()){
            throw new LibroValidationException("El TÍTULO no puede ser NULO");
        }
        if(null == ejemplares || ejemplares <= 0){
            throw new LibroValidationException("Los EJEMPLARES no pueden ser NULOS");
        }
    }

    public void modificarLibro(LibroRequestDTO resquest) {
    }
}
