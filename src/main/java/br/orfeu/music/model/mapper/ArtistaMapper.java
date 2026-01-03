package br.orfeu.music.model.mapper;

import br.orfeu.music.model.Artista;
import br.orfeu.music.model.dto.ArtistaDTO;
import br.orfeu.music.repository.entity.ArtistaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ArtistaMapper {

    Artista toModel(ArtistaDTO dto);

    ArtistaDTO toDTO(Artista model);

    ArtistaEntity toEntity(Artista model);

    Artista toModel(ArtistaEntity entity);
}

