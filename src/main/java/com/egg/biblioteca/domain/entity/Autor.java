package com.egg.biblioteca.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "t_autor",  schema = "biblioteca")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_autor")
    private UUID id;

    @Column(name = "nombre")
    private String nombre;
}
