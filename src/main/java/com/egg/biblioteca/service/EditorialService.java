package com.egg.biblioteca.service;

import com.egg.biblioteca.domain.entity.Editorial;
import com.egg.biblioteca.domain.repository.EditorialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EditorialService {

    private final EditorialRepository editorialRepository;

    @Transactional
    public void crearEditorial(String nombre){
        Editorial editorial = new Editorial();
        editorial.setNombre(nombre);
        editorialRepository.save(editorial);
    }

    @Transactional(readOnly = true)
    public List<Editorial> buscarPorNombre(String nombre){
        return editorialRepository.buscarPorNombre(nombre);
    }
}
