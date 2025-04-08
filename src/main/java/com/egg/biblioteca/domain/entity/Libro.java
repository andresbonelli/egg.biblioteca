package com.egg.biblioteca.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "t_libro",  schema = "biblioteca")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Libro {
    @Id
    @Column(name = "isbn")
    private Long isbn;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "ejemplares")
    private Integer ejemplares;

    @Column(name = "alta")
    private LocalDate alta;

    @ManyToOne
    @JoinColumn(name = "id_autor")
    private Autor autor;

    @ManyToOne
    @JoinColumn(name = "id_editorial")
    private Editorial editorial;
}
