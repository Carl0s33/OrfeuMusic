package br.orfeu.music.config;

import br.orfeu.music.model.Usuario;
import br.orfeu.music.model.UsuarioRole;
import br.orfeu.music.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Classe responsável por inicializar dados padrão no banco de dados
 */
@Component
public class DataInitializer implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DataInitializer(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        // Verifica se já existe algum usuário no banco
        if (usuarioRepository.count() == 0) {
            // Cria o usuário administrador padrão
            Usuario admin = new Usuario();
            admin.setNome("Administrador");
            admin.setEmail("admin@orfeumusic.com");
            admin.setSenha(passwordEncoder.encode("admin123")); // Senha padrão
            admin.setAtivo(true);
            admin.setRoles(new HashSet<>(Arrays.asList(
                UsuarioRole.ROLE_ADMIN,
                UsuarioRole.ROLE_EDITOR,
                UsuarioRole.ROLE_COLABORADOR
            )));
            
            usuarioRepository.save(admin);
            System.out.println("Usuário administrador padrão criado com sucesso!");
        }
    }
}
