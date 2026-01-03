package br.orfeu.music.controller;

import br.orfeu.music.model.Artista;
import br.orfeu.music.model.dto.ArtistaDTO;
import br.orfeu.music.model.mapper.ArtistaMapper;
import br.orfeu.music.service.ArtistaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/artistas")
public class ArtistaController {

    private final ArtistaService artistaService;
    private final ArtistaMapper artistaMapper;

    public ArtistaController(ArtistaService artistaService, ArtistaMapper artistaMapper) {
        this.artistaService = artistaService;
        this.artistaMapper = artistaMapper;
    }

    @GetMapping
    public List<ArtistaDTO> listar() {
        return artistaService.listar().stream()
                .map(artistaMapper::toDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtistaDTO> buscarPorId(@PathVariable Long id) {
        Artista artista = artistaService.buscarPorId(id);
        return ResponseEntity.ok(artistaMapper.toDTO(artista));
    }

    @PostMapping
    public ResponseEntity<ArtistaDTO> criar(@Valid @RequestBody ArtistaDTO dto) {
        Artista novo = artistaMapper.toModel(dto);
        Artista salvo = artistaService.criar(novo);
        ArtistaDTO resposta = artistaMapper.toDTO(salvo);
        return ResponseEntity.created(URI.create("/api/artistas/" + resposta.getId())).body(resposta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArtistaDTO> atualizar(@PathVariable Long id, @Valid @RequestBody ArtistaDTO dto) {
        Artista artista = artistaMapper.toModel(dto);
        Artista atualizado = artistaService.atualizar(id, artista);
        return ResponseEntity.ok(artistaMapper.toDTO(atualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        artistaService.remover(id);
        return ResponseEntity.noContent().build();
    }
}

