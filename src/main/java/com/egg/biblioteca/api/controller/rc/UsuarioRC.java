package com.egg.biblioteca.api.controller.rc;

import com.egg.biblioteca.api.dto.UserRegisterDTO;
import com.egg.biblioteca.api.dto.UserResponseDTO;
import com.egg.biblioteca.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/usuario")
@RequiredArgsConstructor
public class UsuarioRC {

    private final UsuarioService usuarioService;
    private final ObjectMapper objectMapper;

    @GetMapping("/listar")
    public ResponseEntity<List<UserResponseDTO>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    @PostMapping("/registro")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserResponseDTO> registro(@RequestPart String usuarioJson, @RequestPart(required = false) MultipartFile file) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            UserRegisterDTO usuario = objectMapper.readValue(usuarioJson, UserRegisterDTO.class);
            return ResponseEntity.ok(usuarioService.registro(usuario, file));
        } catch (Exception e) {
            log.error("Error registrando usuario");
            return ResponseEntity.internalServerError().build();
        }

    }

    @PutMapping("/modificar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> actualizar(@PathVariable String id, @RequestPart String usuarioJson, @RequestPart(required = false) MultipartFile file) {
        try {
            UserRegisterDTO usuario = objectMapper.readValue(usuarioJson, UserRegisterDTO.class);
            usuarioService.actualizar(
                    java.util.UUID.fromString(id),
                    usuario.nombre(),
                    usuario.email(),
                    usuario.password(),
                    usuario.confirmPassword(),
                    file);
            return ResponseEntity.ok("Usuario actualizado con exito");
        } catch (Exception e) {
            log.error("Error actualizando usuario");
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/eliminar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable String id) {
        usuarioService.eliminar(java.util.UUID.fromString(id));
    }
}
