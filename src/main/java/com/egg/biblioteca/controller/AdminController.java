package com.egg.biblioteca.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping("/dashboard")
    public String panelAdministrativo() {
        return "panel.html";
    }
}
