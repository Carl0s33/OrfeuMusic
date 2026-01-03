package br.orfeu.music.repository;

import br.orfeu.music.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    // Busca um usuário pelo e-mail (username)
    Optional<Usuario> findByEmail(String email);
    
    // Verifica se um e-mail já está em uso
    boolean existsByEmail(String email);
    
    // Busca usuários por papel (role)
    // List<Usuario> findByRolesContaining(UsuarioRole role);
}
