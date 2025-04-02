package com.egg.biblioteca.service;

import com.egg.biblioteca.domain.entity.Editorial;
import com.egg.biblioteca.domain.repository.EditorialRepository;
import com.egg.biblioteca.exception.RegistroNoExisteException;
import com.egg.biblioteca.exception.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EditorialService {

    private final EditorialRepository editorialRepository;

    @Transactional(readOnly = true)
    public List<Editorial> listarEditoriales(){
        return editorialRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Editorial> buscarPorNombre(String nombre){
        return editorialRepository.buscarPorNombre(nombre);
    }

    @Transactional(readOnly = true)
    public Editorial buscarPorId(UUID id){
        return editorialRepository.findById(id).orElseThrow(RegistroNoExisteException::new);
    }

    @Transactional
    public Editorial crearEditorial(String nombre){
        Editorial editorial = new Editorial();
        if (null  == nombre || nombre.isBlank()) {
            throw new ValidationException("El nombre no puede ser VACIO o NULO");
        }
        editorial.setNombre(nombre);
        return editorialRepository.save(editorial);
    }

    @Transactional
    public Editorial modificarEditorial(Editorial editorial){
        return editorialRepository.save(editorial);
    }

    @Transactional
    public void eliminarEditorial(Editorial editorial){
        editorialRepository.delete(editorial);
    }
}
