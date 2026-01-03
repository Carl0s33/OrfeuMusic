package br.orfeu.music.service.impl;

import br.orfeu.music.model.Usuario;
import br.orfeu.music.model.UsuarioRole;
import br.orfeu.music.model.dto.UsuarioDTO;
import br.orfeu.music.model.mapper.UsuarioMapper;
import br.orfeu.music.repository.UsuarioRepository;
import br.orfeu.music.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository,
                         UsuarioMapper usuarioMapper,
                         PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UsuarioDTO criarUsuario(UsuarioDTO usuarioDTO) {
        // Verifica se o e-mail já está em uso
        if (usuarioRepository.existsByEmail(usuarioDTO.getEmail())) {
            throw new IllegalStateException("Já existe um usuário cadastrado com este e-mail");
        }

        // Criptografa a senha
        usuarioDTO.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));

        // Novos usuários são ativos por padrão
        usuarioDTO.setAtivo(true);

        // Converte DTO para entidade e salva
        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
        usuario = usuarioRepository.save(usuario);

        return usuarioMapper.toDTO(usuario);
    }

    public Optional<UsuarioDTO> buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .map(usuarioMapper::toDTO);
    }

    public Optional<UsuarioDTO> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .map(usuarioMapper::toDTO);
    }

    public List<UsuarioDTO> listarTodos() {
        return usuarioRepository.findAll().stream()
                .map(usuarioMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public UsuarioDTO atualizarUsuario(Long id, UsuarioDTO usuarioDTO) {
        return usuarioRepository.findById(id)
                .map(usuarioExistente -> {
                    // Atualiza os campos permitidos
                    usuarioExistente.setNome(usuarioDTO.getNome());

                    // Atualiza a senha apenas se for fornecida
                    if (usuarioDTO.getSenha() != null && !usuarioDTO.getSenha().isEmpty()) {
                        usuarioExistente.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
                    }

                    // Atualiza as roles
                    usuarioExistente.getRoles().clear();
                    usuarioExistente.getRoles().addAll(usuarioMapper.mapRoles(usuarioDTO));

                    // Salva as alterações
                    Usuario usuarioAtualizado = usuarioRepository.save(usuarioExistente);
                    return usuarioMapper.toDTO(usuarioAtualizado);
                })
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + id));
    }

    @Transactional
    public void deletarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    // Método para criar o usuário administrador padrão
    @Transactional
    public void criarAdminPadrao() {
        // Verifica se já existe um admin
        if (usuarioRepository.count() == 0) {
            Usuario admin = new Usuario();
            admin.setNome("Administrador");
            admin.setEmail("admin@orfeumusic.com");
            admin.setSenha(passwordEncoder.encode("admin123")); // Senha padrão - deve ser alterada no primeiro acesso
            admin.getRoles().add(UsuarioRole.ROLE_ADMIN);
            admin.getRoles().add(UsuarioRole.ROLE_EDITOR);
            admin.getRoles().add(UsuarioRole.ROLE_COLABORADOR);
            admin.setAtivo(true);

            usuarioRepository.save(admin);
        }
    }

    @Override
    public boolean existePorEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    @Override
    @Transactional
    public void alterarSenha(Long id, String senhaAtual, String novaSenha) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!passwordEncoder.matches(senhaAtual, usuario.getSenha())) {
            throw new IllegalArgumentException("Senha atual incorreta.");
        }

        usuario.setSenha(passwordEncoder.encode(novaSenha));
        usuarioRepository.save(usuario);
    }
}
