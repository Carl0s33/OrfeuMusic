package br.orfeu.music.security;

import br.orfeu.music.model.dto.UsuarioDTO;
import br.orfeu.music.security.jwt.JwtUtils;
import br.orfeu.music.security.services.UserDetailsImpl;
import br.orfeu.music.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtUtils jwtUtils;

    private String jwtToken;

    @BeforeEach
    void setUp() {
        String email = "testuser_" + System.currentTimeMillis() + "@example.com";
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setNome("Test User");
        usuarioDTO.setEmail(email);
        usuarioDTO.setSenha("password123");
        
        UsuarioDTO savedUser = usuarioService.criarUsuario(usuarioDTO);
        
        UserDetailsImpl userDetails = UserDetailsImpl.builder()
                .id(savedUser.getId())
                .username(savedUser.getEmail())
                .email(savedUser.getEmail())
                .password("password123")
                .build();
        
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        jwtToken = jwtUtils.generateJwtToken(authentication);
    }

    @Test
    void whenAccessPerfilWithValidToken_thenReturns200() throws Exception {
        mockMvc.perform(get("/api/perfil")
                .header("Authorization", "Bearer " + jwtToken)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void whenAccessPerfilWithoutToken_thenReturns401() throws Exception {
        mockMvc.perform(get("/api/perfil")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }
}
