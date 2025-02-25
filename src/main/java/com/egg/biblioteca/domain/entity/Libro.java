package com.egg.biblioteca.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "t_libro",  schema = "biblioteca")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Libro {
    @Column(name = "isbn")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long isbn;
    @Column(name = "titulo")
    private String titulo;
    @Column(name = "ejemplares")
    private Integer ejemplares;
    @Column(name = "alta")
    @Temporal(TemporalType.DATE)
    private Date alta;
    @ManyToOne
    @JoinColumn(name = "id_autor")
    private Autor autor;
    @ManyToOne
    @JoinColumn(name = "id_editorial")
    private Editorial editorial;
}
