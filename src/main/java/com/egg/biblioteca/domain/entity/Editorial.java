package com.egg.biblioteca.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "t_editorial",  schema = "biblioteca")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Editorial {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_editorial")
    private UUID id;

    @Column(name = "nombre")
    private String nombre;

    @OneToMany(mappedBy = "editorial")
    private Set<Libro> libros;
}
