package br.orfeu.music.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
    
    @GetMapping("/all")
    public String allAccess() {
        return "Conteúdo público.";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('COLABORADOR') or hasRole('EDITOR') or hasRole('ADMIN')")
    public String userAccess() {
        return "Conteúdo do usuário.";
    }

    @GetMapping("/colaborador")
    @PreAuthorize("hasRole('COLABORADOR') or hasRole('EDITOR') or hasRole('ADMIN')")
    public String colaboradorAccess() {
        return "Conteúdo do colaborador.";
    }

    @GetMapping("/editor")
    @PreAuthorize("hasRole('EDITOR') or hasRole('ADMIN')")
    public String editorAccess() {
        return "Conteúdo do editor.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Conteúdo do administrador.";
    }
}
