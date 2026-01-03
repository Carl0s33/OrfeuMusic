package br.orfeu.music.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contribuir")
public class ContribuicaoController {

    @GetMapping
    public String index(Model model) {
        model.addAttribute("pagina", "contribuir");
        model.addAttribute("tituloPagina", "Contribuir - Orfeu Music");
        return "contribuir";
    }
}

