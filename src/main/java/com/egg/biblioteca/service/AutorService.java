package com.egg.biblioteca.service;

import com.egg.biblioteca.domain.entity.Autor;
import com.egg.biblioteca.domain.repository.AutorRepository;
import com.egg.biblioteca.exception.RegistroNoExisteException;
import com.egg.biblioteca.exception.RegistroRelacionadoException;
import com.egg.biblioteca.exception.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AutorService {

    private final AutorRepository autorRepository;

    @Transactional(readOnly = true)
    public List<Autor> listarAutores(){
        return autorRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Autor> buscarPorNombre(String nombre){
        return autorRepository.buscarPorNombre(nombre);
    }

    @Transactional(readOnly = true)
    public Autor buscarPorId(UUID id){
        return autorRepository.findById(id).orElseThrow(RegistroNoExisteException::new);
    }

    @Transactional
    public Autor crearAutor(String nombre){
        Autor autor = new Autor();
        if  (nombre == null || nombre.isBlank()) {
            throw new ValidationException("El nombre no puede ser VACIO o NULO");
        }
        autor.setNombre(nombre);
        return autorRepository.save(autor);
    }

    @Transactional
    public Autor modificarAutor(Autor autor){
        autorRepository.findById(autor.getId()).orElseThrow(RegistroNoExisteException::new);
        return autorRepository.save(autor);
    }

    @Transactional
    public void eliminarAutor(Autor autor){
        try {
            autorRepository.deleteById(autor.getId());
        } catch (DataIntegrityViolationException e) {
            throw new RegistroRelacionadoException("El autor posee libros registrados. Eliminar primero los libros correspondientes.");
        }
    }
}
