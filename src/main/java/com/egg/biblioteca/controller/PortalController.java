package com.egg.biblioteca.controller;

import com.egg.biblioteca.controller.dto.UserRegisterDTO;
import com.egg.biblioteca.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class PortalController {

    private final UsuarioService usuarioService;

    @GetMapping("/inicio")
    public String inicio() {
        return "inicio.html";
    }

    @GetMapping("/")
    public String index() {
        return "index.html";
    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, ModelMap modelo ) {
        if (error != null) {
            modelo.put("error", "Usuario o Contraseña inválidos!");
        }
        return "login_form.html";
    }

    @GetMapping("/registrar")
    public String registrar(@RequestParam(required = false) String error, ModelMap model) {
        if (error != null) {
            model.put("error", error);
        }
        return "signup_form.html";
    }

    @PostMapping("/registro")
    public String registro(@ModelAttribute UserRegisterDTO usuario, ModelMap model) {
        usuarioService.registro(usuario);
        model.put("exito", "Usuario registrado con éxito");
        return "redirect:login";
    }
}
