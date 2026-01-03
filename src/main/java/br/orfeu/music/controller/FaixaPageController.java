package br.orfeu.music.controller;

import br.orfeu.music.model.Faixa;
import br.orfeu.music.service.FaixaService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FaixaPageController {

    private final FaixaService faixaService;

    public FaixaPageController(FaixaService faixaService) {
        this.faixaService = faixaService;
    }

    @GetMapping("/faixas")
    public String listarFaixas(Model model) {
        model.addAttribute("faixas", faixaService.listar());
        return "faixas";
    }

    @PostMapping("/faixas/salvar")
    @PreAuthorize("hasRole('COLABORADOR') or hasRole('ADMIN')")
    public String salvarFaixa(@ModelAttribute Faixa faixa) {
        faixaService.criar(faixa);
        return "redirect:/faixas";
    }
}
