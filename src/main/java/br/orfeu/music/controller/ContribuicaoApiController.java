package br.orfeu.music.controller;

import br.orfeu.music.model.Faixa;
import br.orfeu.music.model.dto.ContribuicaoDTO;
import br.orfeu.music.repository.AlbumJpaRepository;
import br.orfeu.music.repository.ArtistaJpaRepository;
import br.orfeu.music.repository.FaixaRepository;
import br.orfeu.music.repository.entity.AlbumEntity;
import br.orfeu.music.repository.entity.ArtistaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/contribuicoes")
public class ContribuicaoApiController {

    @Autowired
    private ArtistaJpaRepository artistaRepository;

    @Autowired
    private AlbumJpaRepository albumRepo;

    @Autowired
    private FaixaRepository faixaRepository;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'EDITOR', 'COLABORADOR')")
    @Transactional
    public ResponseEntity<?> receberContribuicao(@RequestBody ContribuicaoDTO dto) {
        // 1. Find or Create Artist
        ArtistaEntity artista = artistaRepository.findByNome(dto.getNomeArtista())
                .orElseGet(() -> {
                    ArtistaEntity novo = new ArtistaEntity();
                    novo.setNome(dto.getNomeArtista());
                    novo.setNomeVerdadeiro(dto.getNomeVerdadeiro());
                    novo.setDataNascimento(dto.getDataNascimento());
                    novo.setPaisOrigem(dto.getPaisOrigem());
                    novo.setFotoUrl(dto.getFotoArtista());
                    novo.setBiografia(dto.getBiografia());
                    return artistaRepository.save(novo);
                });

        // 2. Create Album
        AlbumEntity album = new AlbumEntity();
        album.setTitulo(dto.getNomeAlbum());
        album.setTipo(dto.getTipoAlbum());
        album.setDataLancamento(dto.getDataLancamento());
        album.setCapaUrl(dto.getUrlCapa());
        album.setGravadora(dto.getGravadora());
        album.setArtista(artista);

        album = albumRepo.save(album);
        // ...

        // 3. Create Tracks
        if (dto.getFaixas() != null) {
            for (ContribuicaoDTO.FaixaContribuicaoDTO f : dto.getFaixas()) {
                Faixa faixa = new Faixa();
                faixa.setTitulo(f.getNome());
                faixa.setDuracao(f.getDuracao());
                faixa.setNumeroFaixa(f.getNumero());
                faixa.setCompositores(f.getCompositores());
                faixa.setProdutores(f.getProdutores());
                faixa.setCreditos(f.getCreditos()); // Feat
                faixa.setAlbumEntity(album);
                faixa.setAlbum(album.getTitulo()); // Legacy field

                faixaRepository.save(faixa);
            }
        }

        return ResponseEntity.ok("Contribuição recebida com sucesso!");
    }
}
