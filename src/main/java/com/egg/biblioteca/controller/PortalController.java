package com.egg.biblioteca.controller;

import com.egg.biblioteca.controller.dto.UserRegisterDTO;
import com.egg.biblioteca.domain.entity.Role;
import com.egg.biblioteca.domain.entity.Usuario;
import com.egg.biblioteca.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class PortalController {

    private final UsuarioService usuarioService;

    @GetMapping("/home")
    public String index(HttpSession session) {
        Usuario authenticatedUser = (Usuario) session.getAttribute("subject");
        /*if (authenticatedUser.getRol().toString().equals("ADMIN")) {
            return "redirect:/admin/dashboard";
        }*/
        return "home";
    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, ModelMap modelo ) {
        if (null != error) {
            modelo.put("error", "Usuario o Contraseña inválidos!");
        }
        return "login_form";
    }

    @GetMapping("/registrar")
    public String registrar(@RequestParam(required = false) String error, ModelMap model) {
        return "signup_form.html";
    }

    @PostMapping("/registro")
    public String registro(@ModelAttribute UserRegisterDTO usuario, ModelMap model) {
        usuarioService.registro(usuario);
        model.put("exito", "Usuario registrado con éxito");
        return "redirect:login";
    }

}
