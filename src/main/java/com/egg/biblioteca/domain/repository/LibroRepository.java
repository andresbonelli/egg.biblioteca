package com.egg.biblioteca.domain.repository;

import com.egg.biblioteca.domain.entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long>{
    @Query("SELECT l FROM Libro l WHERE l.titulo LIKE %:titulo%")
    List<Libro> buscarPorTitulo(String titulo);

    @Query("SELECT l FROM Libro l WHERE l.autor.id = :autorId")
    List<Libro> listarPorAutor(UUID autorId);

    @Query("SELECT l FROM Libro l WHERE l.editorial.id = :editorialId")
    List<Libro> listarPorEditorial(UUID editorialId);
}
