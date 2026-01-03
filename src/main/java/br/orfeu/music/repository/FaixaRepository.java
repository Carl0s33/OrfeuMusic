package br.orfeu.music.repository;

import br.orfeu.music.model.Faixa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FaixaRepository extends JpaRepository<Faixa, Long> {
}

