package br.orfeu.music.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CadastroController {

    @GetMapping("/cadastro")
    public String cadastro(Model model) {
        model.addAttribute("pagina", "cadastro");
        model.addAttribute("tituloPagina", "Cadastro - Orfeu Music");
        return "cadastro";
    }
}
