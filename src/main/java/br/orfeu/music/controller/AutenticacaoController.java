package br.orfeu.music.controller;

import br.orfeu.music.model.dto.UsuarioDTO;
import br.orfeu.music.security.jwt.JwtUtils;
import br.orfeu.music.security.jwt.request.LoginRequest;
import br.orfeu.music.security.jwt.response.MessageResponse;
import br.orfeu.music.security.jwt.response.RespostaLogin;
import br.orfeu.music.security.services.UserDetailsImpl;
import br.orfeu.music.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AutenticacaoController {

    private final AuthenticationManager authenticationManager;
    private final UsuarioService usuarioService;
    private final JwtUtils jwtUtils;

    public AutenticacaoController(AuthenticationManager authenticationManager,
                                  UsuarioService usuarioService,
                                  JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.usuarioService = usuarioService;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/login")
    public ResponseEntity<?> autenticarUsuario(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> permissoes = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new RespostaLogin(
                jwt,
                userDetails.getId(),
                userDetails.getEmail(),
                userDetails.getNome(),
                userDetails.getFotoUrl(),
                permissoes));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registrarUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        if (usuarioService.existePorEmail(usuarioDTO.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Erro: E-mail já está em uso!"));
        }

        usuarioService.criarUsuario(usuarioDTO);

        return ResponseEntity.ok(new MessageResponse("Usuário registrado com sucesso!"));
    }

    @GetMapping("/check")
    public ResponseEntity<?> verificarAutenticacao() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() &&
            !authentication.getName().equals("anonymousUser")) {
            return ResponseEntity.ok().body("Usuário autenticado");
        }
        return ResponseEntity.status(401).body("Não autenticado");
    }
}

