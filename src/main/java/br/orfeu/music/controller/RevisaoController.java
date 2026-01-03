package br.orfeu.music.controller;

import br.orfeu.music.repository.AlbumJpaRepository;
import br.orfeu.music.repository.ArtistaJpaRepository;
import br.orfeu.music.repository.entity.AlbumEntity;
import br.orfeu.music.repository.entity.ArtistaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RevisaoController {

    @Autowired
    private ArtistaJpaRepository artistaRepository;

    @Autowired
    private AlbumJpaRepository albumRepository;

    @GetMapping("/admin/revisoes")
    @PreAuthorize("hasAnyRole('ADMIN', 'EDITOR')")
    public String revisoes(Model model) {
        List<ArtistaEntity> artistasPendentes = artistaRepository.findByStatus("PENDING");
        List<AlbumEntity> albunsPendentes = albumRepository.findByStatus("PENDING");

        model.addAttribute("artistas", artistasPendentes);
        model.addAttribute("albuns", albunsPendentes);
        model.addAttribute("pagina", "revisoes");
        model.addAttribute("tituloPagina", "Revisão de Contribuições - Orfeu Music");
        return "revisoes";
    }

    @RestController
    @RequestMapping("/api/admin/revisoes")
    public static class RevisaoApiController {

        @Autowired
        private ArtistaJpaRepository artistaRepository;

        @Autowired
        private AlbumJpaRepository albumRepository;

        @GetMapping("/pendentes")
        @PreAuthorize("hasAnyRole('ADMIN', 'EDITOR')")
        public ResponseEntity<?> getPendentes() {
            // We need to implement findByStatus in repositories
            return ResponseEntity.ok().build();
        }

        @PostMapping("/artista/{id}/aprovar")
        @PreAuthorize("hasAnyRole('ADMIN', 'EDITOR')")
        public ResponseEntity<?> aprovarArtista(@PathVariable Long id) {
            return artistaRepository.findById(id)
                .map(artista -> {
                    artista.setStatus("APPROVED");
                    artistaRepository.save(artista);
                    return ResponseEntity.ok("Artista aprovado!");
                })
                .orElse(ResponseEntity.notFound().build());
        }

        @PostMapping("/artista/{id}/rejeitar")
        @PreAuthorize("hasAnyRole('ADMIN', 'EDITOR')")
        public ResponseEntity<?> rejeitarArtista(@PathVariable Long id) {
            return artistaRepository.findById(id)
                .map(artista -> {
                    artista.setStatus("REJECTED");
                    artistaRepository.save(artista);
                    return ResponseEntity.ok("Artista rejeitado!");
                })
                .orElse(ResponseEntity.notFound().build());
        }

        @PostMapping("/album/{id}/aprovar")
        @PreAuthorize("hasAnyRole('ADMIN', 'EDITOR')")
        public ResponseEntity<?> aprovarAlbum(@PathVariable Long id) {
            return albumRepository.findById(id)
                .map(album -> {
                    album.setStatus("APPROVED");
                    albumRepository.save(album);
                    return ResponseEntity.ok("Álbum aprovado!");
                })
                .orElse(ResponseEntity.notFound().build());
        }

        @PostMapping("/album/{id}/rejeitar")
        @PreAuthorize("hasAnyRole('ADMIN', 'EDITOR')")
        public ResponseEntity<?> rejeitarAlbum(@PathVariable Long id) {
            return albumRepository.findById(id)
                .map(album -> {
                    album.setStatus("REJECTED");
                    albumRepository.save(album);
                    return ResponseEntity.ok("Álbum rejeitado!");
                })
                .orElse(ResponseEntity.notFound().build());
        }
    }
}

