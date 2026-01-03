package br.orfeu.music.controller;

import br.orfeu.music.model.dto.ArtistaDTO;
import br.orfeu.music.service.ArtistaService;
import br.orfeu.music.model.mapper.ArtistaMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/artistas")
public class ArtistaPageController {

    private final ArtistaService artistaService;
    private final ArtistaMapper artistaMapper;

    public ArtistaPageController(ArtistaService artistaService, ArtistaMapper artistaMapper) {
        this.artistaService = artistaService;
        this.artistaMapper = artistaMapper;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("artistas", artistaService.listar().stream().map(artistaMapper::toDTO).toList());
        model.addAttribute("pagina", "artistas");
        model.addAttribute("tituloPagina", "Artistas - Orfeu Music");
        return "artistas";
    }
}
