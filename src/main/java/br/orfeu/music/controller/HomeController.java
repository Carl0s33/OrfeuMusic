package br.orfeu.music.controller;

import br.orfeu.music.repository.AlbumJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private AlbumJpaRepository albumRepository;

    // Record simples para representar um álbum
    public record Album(String titulo, String artista, String urlCapa) {}

    @GetMapping("/")
    public String home(Model model) {
        List<Album> bestSellers = List.of(
            new Album("Acabou Chorare", "Novos Baianos", "https://upload.wikimedia.org/wikipedia/pt/3/33/Acabou_Chorare.jpg"),
            new Album("Clube da Esquina", "Milton Nascimento & Lô Borges", "https://upload.wikimedia.org/wikipedia/pt/0/01/Clube_da_Esquina.jpg"),
            new Album("Chega de Saudade", "João Gilberto", "https://upload.wikimedia.org/wikipedia/pt/c/c2/Chega_de_Saudade.jpg"),
            new Album("Elis & Tom", "Elis Regina & Tom Jobim", "https://upload.wikimedia.org/wikipedia/pt/1/15/Elis_%26_Tom.jpg"),
            new Album("Construção", "Chico Buarque", "https://upload.wikimedia.org/wikipedia/pt/b/b5/Constru%C3%A7%C3%A3o.jpg"),
            new Album("Secos & Molhados", "Secos & Molhados", "https://upload.wikimedia.org/wikipedia/pt/0/03/Secos_e_Molhados_-_1973.jpg"),
            new Album("Tropicália ou Panis et Circencis", "Vários Artistas", "https://upload.wikimedia.org/wikipedia/pt/3/39/Tropicalia-panis-et-circensis.jpg"),
            new Album("Cartola", "Cartola", "https://upload.wikimedia.org/wikipedia/pt/2/22/Cartola_-_1976.jpg"),
            new Album("Da Lama ao Caos", "Chico Science & Nação Zumbi", "https://upload.wikimedia.org/wikipedia/pt/c/c3/Da_lama_ao_caos.jpg"),
            new Album("Sobrevivendo no Inferno", "Racionais MC's", "https://upload.wikimedia.org/wikipedia/pt/2/2a/Racionais-MCs-Sobrevivendo-no-Inferno-1997.jpg"),
            new Album("A Tábua de Esmeralda", "Jorge Ben", "https://upload.wikimedia.org/wikipedia/pt/2/25/A_T%C3%A1bua_de_Esmeralda.jpg"),
            new Album("Krig-ha, Bandolo!", "Raul Seixas", "https://upload.wikimedia.org/wikipedia/pt/e/e3/Krig-ha%2C_Bandolo%21.jpg")
        );

        model.addAttribute("pagina", "inicial");
        model.addAttribute("tituloPagina", "Início - Orfeu Music");
        model.addAttribute("bestSellers", bestSellers);
        return "index";
    }

    @GetMapping("/lancamentos")
    public String lancamentos(Model model) {
        var lancamentos = albumRepository.findByStatus("APPROVED");

        model.addAttribute("pagina", "lancamentos");
        model.addAttribute("tituloPagina", "Álbuns - Orfeu Music");
        model.addAttribute("lancamentos", lancamentos);
        return "lancamentos";
    }

    @GetMapping("/selos")
    public String selos(Model model) {
        model.addAttribute("pagina", "selos");
        model.addAttribute("tituloPagina", "Selos - Orfeu Music");
        return "selos";
    }




    @GetMapping("/historico")
    public String historico(Model model) {
        model.addAttribute("pagina", "historico");
        model.addAttribute("tituloPagina", "Histórico - Orfeu Music");
        return "historico";
    }
}
