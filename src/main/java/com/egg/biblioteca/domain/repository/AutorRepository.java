package com.egg.biblioteca.domain.repository;

import com.egg.biblioteca.domain.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AutorRepository extends JpaRepository<Autor, UUID>{
    @Query("SELECT a FROM Autor a WHERE a.nombre LIKE %:nombre%")
    public List<Autor> buscarPorNombre(String nombre);
}
