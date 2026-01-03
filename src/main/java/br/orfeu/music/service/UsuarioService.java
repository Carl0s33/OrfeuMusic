package br.orfeu.music.service;

import br.orfeu.music.model.dto.UsuarioDTO;
import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    UsuarioDTO criarUsuario(UsuarioDTO usuarioDTO);
    Optional<UsuarioDTO> buscarPorId(Long id);
    Optional<UsuarioDTO> buscarPorEmail(String email);
    List<UsuarioDTO> listarTodos();
    UsuarioDTO atualizarUsuario(Long id, UsuarioDTO usuarioDTO);
    void deletarUsuario(Long id);
    void criarAdminPadrao();
    boolean existePorEmail(String email);
    void alterarSenha(Long id, String senhaAtual, String novaSenha);
}
