package br.orfeu.music.repository;

import br.orfeu.music.repository.entity.ArtistaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArtistaJpaRepository extends JpaRepository<ArtistaEntity, Long> {
    // Busca artista por nome
    Optional<ArtistaEntity> findByNome(String nome);

    List<ArtistaEntity> findByStatus(String status);
}
