package com.egg.biblioteca.service;

import com.egg.biblioteca.domain.entity.Autor;
import com.egg.biblioteca.domain.entity.Libro;
import com.egg.biblioteca.domain.repository.AutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AutorService {

    private final AutorRepository autorRepository;

    @Transactional
    public void crearAutor(String nombre){
        Autor autor = new Autor();
        autor.setNombre(nombre);
        autorRepository.save(autor);
    }

    @Transactional(readOnly = true)
    public List<Autor> buscarPorNombre(String nombre){
        return autorRepository.buscarPorNombre(nombre);
    }

    @Transactional(readOnly = true)
    public List<Autor> listarAutores(){
        return autorRepository.findAll();
    }


    @Transactional
    public void eliminarAutor(Autor autor){
        autorRepository.delete(autor);
    }
}
