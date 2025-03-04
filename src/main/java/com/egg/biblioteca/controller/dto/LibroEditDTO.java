package com.egg.biblioteca.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibroEditDTO {
    private Long isbn;
    private String titulo;
    private Integer ejemplares;
    private String autorId;
    private String editorialId;
}
