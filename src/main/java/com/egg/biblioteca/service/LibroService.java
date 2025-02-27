package com.egg.biblioteca.service;

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
    public void crearLibro(Long isbn, String titulo, Integer ejemplares, String nombreAutor, String nombreEditorial) {
        validar(isbn, titulo, ejemplares, nombreAutor, nombreEditorial);
        if (libroRepository.existsById(isbn)) {
            throw new LibroValidationException("El ISBN ya existe.");
        }
        Libro libro = new Libro();
        libro.setIsbn(isbn);
        libro.setTitulo(titulo);
        libro.setEjemplares(ejemplares);
        libro.setAlta(new Date());
        try {
            libro.setAutor(autorService.buscarPorNombre(nombreAutor).get(0));
            libro.setEditorial(editorialService.buscarPorNombre(nombreEditorial).get(0));
        } catch (Exception e) {
            log.error("Error al crear el libro: {}", e.getMessage());
            throw new LibroValidationException("Editorial o Autor no existente.");
        }
        libroRepository.save(libro);
    }

    @Transactional
    public void modificarLibro(Long isbn, String titulo, Integer ejemplares, String nombreAutor, String nombreEditorial) {
        validar(isbn, titulo, ejemplares, nombreAutor, nombreEditorial);
        Libro libro = libroRepository.findById(isbn).orElseThrow(RegistroNoExisteException::new);
        libro.setTitulo(titulo);
        libro.setEjemplares(ejemplares);
        try {
            libro.setAutor(autorService.buscarPorNombre(nombreAutor).get(0));
            libro.setEditorial(editorialService.buscarPorNombre(nombreEditorial).get(0));
        } catch (Exception e) {
            log.error("Error al modificar el libro: {}", e.getMessage());
            throw new LibroValidationException("Editorial o Autor no existente.");
        }
        libroRepository.save(libro);
    }

    @Transactional
    public void eliminarLibro(Long isbn) {
        Libro libro = libroRepository.findById(isbn).orElseThrow(RegistroNoExisteException::new);
        libroRepository.delete(libro);
    }

    private void validar (Long isbn, String titulo, Integer ejemplares, String nombreAutor, String nombreEditorial) throws LibroValidationException{
        if (null == isbn){
            throw new LibroValidationException("El ISBN no puede ser NULO.");
        }
        if(null == titulo || titulo.isBlank()){
            throw new LibroValidationException("El TÍTULO no puede ser NULO");
        }
        if(null == ejemplares || ejemplares <= 0){
            throw new LibroValidationException("Los EJEMPLARES no pueden ser NULOS");
        }
        if (null == nombreAutor || nombreAutor.isBlank()){
            throw new LibroValidationException("El AUTOR no puede ser NULO");
        }
        if (null == nombreEditorial || nombreEditorial.isBlank()){
            throw new LibroValidationException("La EDITORIAL no puede ser NULA");
        }
    }

}
