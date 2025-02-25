package com.egg.biblioteca.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "t_autor",  schema = "biblioteca")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Autor {
    @Column(name = "id_autor")
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(mappedBy = "autor")
    private Set<Libro> libros;
}
