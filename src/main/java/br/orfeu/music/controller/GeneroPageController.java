package br.orfeu.music.controller;

import br.orfeu.music.model.GeneroEnum;
import br.orfeu.music.model.dto.GeneroDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/generos")
public class GeneroPageController {

    @GetMapping
    public String listar(Model model) {
        List<GeneroDTO> generos = Arrays.stream(GeneroEnum.values())
            .map(g -> {
                GeneroDTO dto = new GeneroDTO();
                dto.setNome(g.getDescricao());
                dto.setDescricao("Gênero musical " + g.getDescricao());
                dto.setStatus("aprovado");
                return dto;
            })
            .toList();

        model.addAttribute("generos", generos);
        model.addAttribute("pagina", "generos");
        model.addAttribute("tituloPagina", "Gêneros - Orfeu Music");
        return "generos";
    }
}
