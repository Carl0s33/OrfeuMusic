package br.orfeu.music.config;

import br.orfeu.music.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserChecker {

    @Bean
    public CommandLineRunner checkUsers(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            System.out.println("--- CHECKING USERS ---");
            usuarioRepository.findAll().forEach(u -> {
                System.out.println("User: " + u.getEmail());
                System.out.println("Active: " + u.isAtivo());
                System.out.println("Roles: " + u.getRoles());
                System.out.println("Password matches 'admin123': " + passwordEncoder.matches("admin123", u.getSenha()));
                System.out.println("-------------------");
            });

            if (usuarioRepository.findByEmail("admin@orfeu.com").isEmpty()) {
                System.out.println("WARNING: admin@orfeu.com NOT FOUND!");
            }
        };
    }
}

