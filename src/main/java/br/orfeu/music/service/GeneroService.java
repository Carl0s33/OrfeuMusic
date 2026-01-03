package br.orfeu.music.service;

import br.orfeu.music.model.Genero;

import java.util.List;

public interface GeneroService {

    List<Genero> listar();

    Genero buscarPorId(Long id);

    Genero criar(Genero genero);

    Genero atualizar(Long id, Genero genero);

    void remover(Long id);
}
