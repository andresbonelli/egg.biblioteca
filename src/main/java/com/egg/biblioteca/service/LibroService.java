package com.egg.biblioteca.service;

import com.egg.biblioteca.api.dto.LibroRequestDTO;
import com.egg.biblioteca.api.dto.LibroResponseDTO;
import com.egg.biblioteca.domain.entity.Libro;
import com.egg.biblioteca.domain.repository.LibroRepository;
import com.egg.biblioteca.exception.ValidationException;
import com.egg.biblioteca.exception.RegistroNoExisteException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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
    public List<LibroResponseDTO> listarLibros() {
        List<Libro> libros = libroRepository.findAll();
        if (libros.isEmpty()) {
            throw new RegistroNoExisteException();
        }
        return libros.stream().map(this::getLibroResponseDTO).toList();
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
    public LibroResponseDTO crearLibro(LibroRequestDTO libro) {
        validar(libro.isbn(), libro.titulo(), libro.ejemplares());
        if (libroRepository.existsById(libro.isbn())) {
            throw new ValidationException("El ISBN ya existe.");
        }
        Libro nuevoLibro = new Libro();
        nuevoLibro.setIsbn(libro.isbn());
        nuevoLibro.setTitulo(libro.titulo());
        nuevoLibro.setEjemplares(libro.ejemplares());
        try {
            nuevoLibro.setAutor(autorService.buscarPorId(UUID.fromString(libro.autorId())));
            nuevoLibro.setEditorial(editorialService.buscarPorId(UUID.fromString(libro.editorialId())));
        } catch (Exception e) {
            log.error("Error al crear el libro: {}", e.getMessage());
            throw new ValidationException("Editorial o Autor no existente.");
        }
        nuevoLibro.setAlta(LocalDate.now());
        return getLibroResponseDTO(libroRepository.save(nuevoLibro));
    }

    @Transactional
    public LibroResponseDTO modificarLibro(LibroRequestDTO libro) {
        validar(libro.isbn(), libro.titulo(), libro.ejemplares());
        Libro libroModificado = libroRepository.findById(libro.isbn()).orElseThrow(RegistroNoExisteException::new);
        libroModificado.setTitulo(libro.titulo());
        libroModificado.setEjemplares(libro.ejemplares());
        try {
            libroModificado.setAutor(autorService.buscarPorId(UUID.fromString(libro.autorId())));
            libroModificado.setEditorial(editorialService.buscarPorId(UUID.fromString(libro.editorialId())));
        } catch (Exception e) {
            log.error("Error al modificar el libro: {}", e.getMessage());
            throw new ValidationException("Editorial o Autor no existente.");
        }
        return getLibroResponseDTO(libroRepository.save(libroModificado));
    }

    @Transactional
    public void eliminarLibro(Long isbn) {
        Libro libro = libroRepository.findById(isbn).orElseThrow(RegistroNoExisteException::new);
        libroRepository.delete(libro);
    }

    private void validar (Long isbn, String titulo, Integer ejemplares) throws ValidationException {
        if (null == isbn){
            throw new ValidationException("El ISBN no puede ser NULO.");
        }
        if(null == titulo || titulo.isBlank()){
            throw new ValidationException("El TÍTULO no puede ser NULO");
        }
        if(null == ejemplares || ejemplares <= 0){
            throw new ValidationException("Los EJEMPLARES no pueden ser NULOS");
        }
    }

    private LibroResponseDTO getLibroResponseDTO(Libro libro) {
        return new LibroResponseDTO(
                libro.getIsbn(),
                libro.getTitulo(),
                libro.getEjemplares(),
                libro.getAlta(),
                libro.getAutor().getNombre(),
                libro.getEditorial().getNombre()
        );
    }
}
