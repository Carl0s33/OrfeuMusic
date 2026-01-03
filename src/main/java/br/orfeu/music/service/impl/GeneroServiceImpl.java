package br.orfeu.music.service.impl;

import br.orfeu.music.model.Genero;
import br.orfeu.music.model.mapper.GeneroMapper;
import br.orfeu.music.repository.GeneroJpaRepository;
import br.orfeu.music.repository.entity.GeneroEntity;
import br.orfeu.music.service.GeneroService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneroServiceImpl implements GeneroService {

    private final GeneroJpaRepository generoJpaRepository;
    private final GeneroMapper generoMapper;

    public GeneroServiceImpl(GeneroJpaRepository generoJpaRepository, GeneroMapper generoMapper) {
        this.generoJpaRepository = generoJpaRepository;
        this.generoMapper = generoMapper;
    }

    @Override
    public List<Genero> listar() {
        return generoJpaRepository.findAll().stream()
                .map(generoMapper::toModel)
                .toList();
    }

    @Override
    public Genero buscarPorId(Long id) {
        return generoJpaRepository.findById(id)
                .map(generoMapper::toModel)
                .orElseThrow();
    }

    @Override
    public Genero criar(Genero genero) {
        GeneroEntity entity = generoMapper.toEntity(genero);
        entity = generoJpaRepository.save(entity);
        return generoMapper.toModel(entity);
    }

    @Override
    public Genero atualizar(Long id, Genero genero) {
        GeneroEntity existente = generoJpaRepository.findById(id).orElseThrow();
        existente.setNome(genero.getNome());
        existente.setDescricao(genero.getDescricao());
        existente.setStatus(genero.getStatus());
        existente.setNumeroArtistas(genero.getNumeroArtistas());
        existente.setNumeroLancamentos(genero.getNumeroLancamentos());
        existente.setNumeroFaixas(genero.getNumeroFaixas());
        existente = generoJpaRepository.save(existente);
        return generoMapper.toModel(existente);
    }

    @Override
    public void remover(Long id) {
        generoJpaRepository.deleteById(id);
    }
}

