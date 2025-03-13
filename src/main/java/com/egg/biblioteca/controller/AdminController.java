package com.egg.biblioteca.controller;

import com.egg.biblioteca.domain.entity.Usuario;
import com.egg.biblioteca.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    private UsuarioService usuarioService;


    @GetMapping("/usuarios")
    public String listar(ModelMap modelo){
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        modelo.addAttribute("usuarios", usuarios);
        return "usuario_list";
    }

    @GetMapping("/modificarRol/{id}")
    public String cambiarRol(@PathVariable UUID id){
        usuarioService.cambiarRol(id);
        return "redirect:usuarios";
    }

    @GetMapping("/usuario/{id}")
    public String modificarUsuario(@PathVariable UUID id, ModelMap modelo){
        Usuario usuario = usuarioService.buscarPorId(id);
        modelo.addAttribute("usuario", usuario);
        return "usuario_modificar";
    }


    @PostMapping("/modificar/{id}")
    public String modificar(MultipartFile archivo, @PathVariable UUID id, @RequestParam String nombre, @RequestParam String email,
                            @RequestParam String password, @RequestParam String confirmPassword, ModelMap modelo) {

        try{
            usuarioService.actualizar(archivo, id, nombre, email, password, confirmPassword);
            modelo.put("exito", "El usuario fue actualizado correctamente.");
            return "redirect:/admin/usuarios";
        } catch (Exception ex) {
            modelo.put("error", ex.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("email", email);
            return "usuario_modificar.html";
        }
    }
}
