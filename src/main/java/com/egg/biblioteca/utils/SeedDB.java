package com.egg.biblioteca.utils;


import com.egg.biblioteca.service.AutorService;
import com.egg.biblioteca.service.EditorialService;
import com.egg.biblioteca.service.LibroService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SeedDB implements CommandLineRunner {

    private final AutorService autorService;
    private final EditorialService editorialService;
    private final LibroService libroService;

    @Override
    public void run(String... args) throws Exception {
        try {
            autorService.crearAutor("Gabriel García Márquez");
            autorService.crearAutor("Jorge Luis Borges");
            autorService.crearAutor("Isabel Allende");
            autorService.crearAutor("Mario Vargas Llosa");

            editorialService.crearEditorial("Planeta");
            editorialService.crearEditorial("Santillana");

            libroService.crearLibro(9788437604947L, "Cien años de soledad", 10, "Gabriel García Márquez", "Planeta");
            libroService.crearLibro(9788437604948L, "El amor en los tiempos del cólera", 10, "Gabriel García Márquez", "Planeta");
            libroService.crearLibro(9788437604949L, "El Aleph", 10, "Jorge Luis Borges", "Santillana");

            log.info("Datos cargados correctamente.");
        } catch (Exception e) {
            log.error("Error cargando datos: {}",e.getMessage());
        }
    }
}
