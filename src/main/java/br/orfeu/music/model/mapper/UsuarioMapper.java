package br.orfeu.music.model.mapper;

import br.orfeu.music.model.Usuario;
import br.orfeu.music.model.UsuarioRole;
import br.orfeu.music.model.dto.UsuarioDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    
    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);
    
    @Mapping(target = "senha", ignore = true) // Não mapeia a senha por padrão
    @Mapping(target = "colaborador", expression = "java(hasRole(usuario, UsuarioRole.ROLE_COLABORADOR))")
    @Mapping(target = "editor", expression = "java(hasRole(usuario, UsuarioRole.ROLE_EDITOR))")
    @Mapping(target = "fotoUrl", source = "fotoUrl")
    UsuarioDTO toDTO(Usuario usuario);
    
    @Mapping(target = "roles", expression = "java(mapRoles(usuarioDTO))")
    @Mapping(target = "fotoUrl", source = "fotoUrl")
    Usuario toEntity(UsuarioDTO usuarioDTO);
    
    default Set<UsuarioRole> mapRoles(UsuarioDTO dto) {
        Set<UsuarioRole> roles = new java.util.HashSet<>();
        
        // Por padrão, todo usuário tem papel de usuário comum e visitante
        roles.add(UsuarioRole.ROLE_VISITANTE);
        roles.add(UsuarioRole.ROLE_USER);
        
        if (dto.isColaborador()) {
            roles.add(UsuarioRole.ROLE_COLABORADOR);
        }
        
        if (dto.isEditor()) {
            roles.add(UsuarioRole.ROLE_EDITOR);
        }
        
        // Se for admin, adiciona todas as roles
        if (dto.isAdmin()) {
            roles.add(UsuarioRole.ROLE_ADMIN);
            roles.add(UsuarioRole.ROLE_EDITOR);
            roles.add(UsuarioRole.ROLE_COLABORADOR);
        }
        
        return roles;
    }
    
    default boolean hasRole(Usuario usuario, UsuarioRole role) {
        return usuario != null && usuario.getRoles() != null && 
               usuario.getRoles().contains(role);
    }
}
