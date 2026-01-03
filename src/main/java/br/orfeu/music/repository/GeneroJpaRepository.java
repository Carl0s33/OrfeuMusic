package br.orfeu.music.repository;

import br.orfeu.music.repository.entity.GeneroEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneroJpaRepository extends JpaRepository<GeneroEntity, Long> {
}

