package br.orfeu.music.model.mapper;

import br.orfeu.music.model.Genero;
import br.orfeu.music.model.dto.GeneroDTO;
import br.orfeu.music.repository.entity.GeneroEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GeneroMapper {

    Genero toModel(GeneroDTO dto);

    GeneroDTO toDTO(Genero model);

    GeneroEntity toEntity(Genero model);

    Genero toModel(GeneroEntity entity);
}

