package br.orfeu.music.controller;

import br.orfeu.music.model.dto.UsuarioDTO;
import br.orfeu.music.security.services.UserDetailsImpl;
import br.orfeu.music.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/perfil")
public class PerfilController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('COLABORADOR') or hasRole('EDITOR') or hasRole('ADMIN')")
    public ResponseEntity<?> obterPerfil() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        
        return ResponseEntity.ok(usuarioService.buscarPorId(userDetails.getId()));
    }

    @PutMapping
    @PreAuthorize("hasRole('USER') or hasRole('COLABORADOR') or hasRole('EDITOR') or hasRole('ADMIN')")
    public ResponseEntity<?> atualizarPerfil(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        
        // Garante que o usuário só pode atualizar seu próprio perfil
        if (!userDetails.getId().equals(usuarioDTO.getId())) {
            return ResponseEntity.badRequest().body("Você só pode atualizar seu próprio perfil.");
        }
        
        return ResponseEntity.ok(usuarioService.atualizarUsuario(usuarioDTO.getId(), usuarioDTO));
    }

    @PutMapping("/alterar-senha")
    @PreAuthorize("hasRole('USER') or hasRole('COLABORADOR') or hasRole('EDITOR') or hasRole('ADMIN')")
    public ResponseEntity<?> alterarSenha(@RequestParam String senhaAtual, 
                                         @RequestParam String novaSenha) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        
        try {
            usuarioService.alterarSenha(userDetails.getId(), senhaAtual, novaSenha);
            return ResponseEntity.ok("Senha alterada com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Erro ao alterar senha: " + e.getMessage());
        }
    }
}
