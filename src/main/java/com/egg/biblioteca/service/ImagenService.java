package com.egg.biblioteca.service;

import com.egg.biblioteca.domain.entity.Imagen;
import com.egg.biblioteca.domain.repository.ImagenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImagenService {

    private final ImagenRepository imagenRepository;

    @Transactional(readOnly = true)
    public List<Imagen> listarImagenes() {
        return imagenRepository.findAll();
    }

    public Imagen guardar(MultipartFile file) {
        if (file != null) {
            try {
                Imagen imagen = new Imagen();
                imagen.setMime(file.getContentType());
                imagen.setNombre(file.getName());
                imagen.setContenido(file.getBytes());
                return imagenRepository.save(imagen);
            } catch (Exception e) {
                log.error("Error al guardar la imagen", e);
            }
        }
        return null;
    }

    public Imagen actualizar(MultipartFile file, UUID idImagen) {
        if (file != null) {
            try {
                Imagen imagen = new Imagen();
                if (idImagen != null) {
                    imagen.setMime(file.getContentType());
                    imagen.setNombre(file.getName());
                    imagen.setContenido(file.getBytes());
                    return imagenRepository.save(imagen);
                }
            } catch (Exception e) {
                log.error("Error al guardar la imagen", e);
            }
        }
        return null;
    }
}
