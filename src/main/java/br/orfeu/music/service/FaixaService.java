package br.orfeu.music.service;

import br.orfeu.music.model.Faixa;

import java.util.List;

public interface FaixaService {

    List<Faixa> listar();

    Faixa buscarPorId(Long id);

    Faixa criar(Faixa faixa);

    Faixa atualizar(Long id, Faixa faixa);

    void remover(Long id);
}

