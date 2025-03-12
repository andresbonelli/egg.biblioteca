package com.egg.biblioteca.controller;

import com.egg.biblioteca.domain.entity.Usuario;
import com.egg.biblioteca.service.ImagenService;
import com.egg.biblioteca.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Controller
@RequestMapping("/imagen")
@RequiredArgsConstructor
public class ImagenController {

    private final ImagenService imagenService;
    private final UsuarioService usuarioService;

    @GetMapping("/perfil/{id}")
    public ResponseEntity<byte[]> imagenUsuario(@PathVariable UUID id) {
        Usuario usuario = usuarioService.buscarPorId(id);
        byte[] imagen = usuario.getImagen().getContenido();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(imagen, headers, HttpStatus.OK);
    }

    @PostMapping("/perfil/{id}")
    public ResponseEntity<String> actualizarImagenUsuario(
            @PathVariable UUID id,
            @RequestParam("archivo") MultipartFile archivo) {
        try {
            imagenService.actualizar(archivo, id);
            return new ResponseEntity<>("Imagen actualizada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar la imagen", HttpStatus.BAD_REQUEST);
        }
    }
}
