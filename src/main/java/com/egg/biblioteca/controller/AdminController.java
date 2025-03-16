package com.egg.biblioteca.controller;

import com.egg.biblioteca.domain.entity.Usuario;
import com.egg.biblioteca.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UsuarioService usuarioService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/usuarios")
    public String listar(ModelMap modelo){
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        modelo.addAttribute("usuarios", usuarios);
        return "usuario_list";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/modificarRol/{id}")
    public String cambiarRol(@PathVariable("id") String id){
        UUID uuid = UUID.fromString(id);
        usuarioService.cambiarRol(uuid);
        return "redirect:usuarios";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/usuario/{id}")
    public String modificarUsuario(@PathVariable("id") String id, ModelMap modelo){
        UUID uuid = UUID.fromString(id);
        Usuario usuario = usuarioService.buscarPorId(uuid);
        if (usuario == null) {
            // Handle the case where the user doesn't exist
            modelo.put("error", "Usuario no encontrado.");
            return "redirect:usuarios"; // Redirect to the user list or an error page
        }
        modelo.addAttribute("usuario", usuario);
        return "usuario_modificar";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @PostMapping("/modificar/{id}")
    public String modificar(MultipartFile archivo, @PathVariable("id") String id, @RequestParam String nombre, @RequestParam String email,
                            @RequestParam String password, @RequestParam String confirmPassword, ModelMap modelo) {

        try{
            UUID uuid = UUID.fromString(id);
            usuarioService.actualizar(archivo, uuid, nombre, email, password, confirmPassword);
            modelo.put("exito", "El usuario fue actualizado correctamente.");
            return "redirect:usuarios";
        } catch (Exception ex) {
            modelo.put("error", ex.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("email", email);
            return "usuario_modificar.html";
        }
    }
}
