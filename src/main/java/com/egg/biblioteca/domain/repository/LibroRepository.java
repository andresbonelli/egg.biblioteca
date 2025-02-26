package com.egg.biblioteca.domain.repository;

import com.egg.biblioteca.domain.entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long>{
    @Query("SELECT l FROM Libro l WHERE l.titulo LIKE %:titulo%")
    List<Libro> listarPorTitulo(String titulo);

    @Query("SELECT l FROM Libro l WHERE l.autor.nombre LIKE %:nombre%")
    List<Libro> listarPorAutor(String nombre);

    @Query("SELECT l FROM Libro l WHERE l.editorial.nombre LIKE %:nombre%")
    List<Libro> listarPorEditorial(String nombre);
}
