package br.orfeu.music.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PublicAccessIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void whenAccessHomePage_thenReturns200() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    void whenAccessArtistasPage_thenReturns200() throws Exception {
        mockMvc.perform(get("/artistas"))
                .andExpect(status().isOk());
    }

    @Test
    void whenAccessGenerosPage_thenReturns200() throws Exception {
        mockMvc.perform(get("/generos"))
                .andExpect(status().isOk());
    }

    @Test
    void whenAccessArtistasApi_thenReturns200() throws Exception {
        mockMvc.perform(get("/api/artistas")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void whenAccessPublicApi_thenReturns200() throws Exception {
        mockMvc.perform(get("/api/test/all")
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
