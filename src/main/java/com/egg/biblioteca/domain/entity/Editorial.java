package com.egg.biblioteca.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "t_editorial",  schema = "biblioteca")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Editorial {
    @Column(name = "id_editorial")
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(mappedBy = "editorial")
    private Set<Libro> libros;
}
