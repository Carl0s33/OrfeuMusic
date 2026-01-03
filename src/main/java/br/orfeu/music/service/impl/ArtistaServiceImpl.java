package br.orfeu.music.service.impl;

import br.orfeu.music.model.Artista;
import br.orfeu.music.model.mapper.ArtistaMapper;
import br.orfeu.music.repository.ArtistaJpaRepository;
import br.orfeu.music.repository.entity.ArtistaEntity;
import br.orfeu.music.service.ArtistaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistaServiceImpl implements ArtistaService {

    private final ArtistaJpaRepository artistaJpaRepository;
    private final ArtistaMapper artistaMapper;

    public ArtistaServiceImpl(ArtistaJpaRepository artistaJpaRepository, ArtistaMapper artistaMapper) {
        this.artistaJpaRepository = artistaJpaRepository;
        this.artistaMapper = artistaMapper;
    }

    @Override
    public List<Artista> listar() {
        return artistaJpaRepository.findAll().stream()
                .map(artistaMapper::toModel)
                .toList();
    }

    @Override
    public Artista buscarPorId(Long id) {
        ArtistaEntity entity = artistaJpaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Artista não encontrado"));
        return artistaMapper.toModel(entity);
    }

    @Override
    public Artista criar(Artista artista) {
        ArtistaEntity entity = artistaMapper.toEntity(artista);
        ArtistaEntity salvo = artistaJpaRepository.save(entity);
        return artistaMapper.toModel(salvo);
    }

    @Override
    public Artista atualizar(Long id, Artista artista) {
        ArtistaEntity existente = artistaJpaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Artista não encontrado"));

        existente.setNome(artista.getNome());
        existente.setPaisOrigem(artista.getPaisOrigem());
        existente.setPeriodoAtividade(artista.getPeriodoAtividade());
        existente.setEstilos(artista.getEstilos());

        ArtistaEntity salvo = artistaJpaRepository.save(existente);
        return artistaMapper.toModel(salvo);
    }

    @Override
    public void remover(Long id) {
        artistaJpaRepository.deleteById(id);
    }
}


