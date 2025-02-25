package com.egg.biblioteca.domain.repository;

import com.egg.biblioteca.domain.entity.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EditorialRepository extends JpaRepository<Editorial, UUID>{
    @Query("SELECT e FROM Editorial e WHERE e.nombre LIKE %:nombre%")
    List<Editorial> buscarPorNombre(String nombre);
}
