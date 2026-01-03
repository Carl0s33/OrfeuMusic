package br.orfeu.music.service;

import br.orfeu.music.model.Artista;

import java.util.List;

public interface ArtistaService {

    List<Artista> listar();

    Artista buscarPorId(Long id);

    Artista criar(Artista artista);

    Artista atualizar(Long id, Artista artista);

    void remover(Long id);
}

