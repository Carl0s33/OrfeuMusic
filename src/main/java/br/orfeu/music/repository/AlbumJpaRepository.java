package br.orfeu.music.repository;

import br.orfeu.music.repository.entity.AlbumEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AlbumJpaRepository extends JpaRepository<AlbumEntity, Long> {

    // Repositório de álbuns
    List<AlbumEntity> findByStatusAndDataLancamentoBetween(String status, LocalDate start, LocalDate end);

    List<AlbumEntity> findByStatus(String status);
}
