package com.egg.biblioteca.domain.repository;

import com.egg.biblioteca.domain.entity.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ImagenRepository extends JpaRepository<Imagen, UUID> {
}
