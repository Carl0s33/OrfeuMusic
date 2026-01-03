package br.orfeu.music.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ArtistaDTO {

    private Long id;

    @NotBlank
    @Size(max = 200)
    private String nome;

    @Size(max = 100)
    private String paisOrigem;

    @Size(max = 100)
    private String periodoAtividade;

    @Size(max = 255)
    private String estilos;

    private String fotoUrl;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getPaisOrigem() { return paisOrigem; }
    public void setPaisOrigem(String paisOrigem) { this.paisOrigem = paisOrigem; }

    public String getPeriodoAtividade() { return periodoAtividade; }
    public void setPeriodoAtividade(String periodoAtividade) { this.periodoAtividade = periodoAtividade; }

    public String getEstilos() { return estilos; }
    public void setEstilos(String estilos) { this.estilos = estilos; }

    public String getFotoUrl() { return fotoUrl; }
    public void setFotoUrl(String fotoUrl) { this.fotoUrl = fotoUrl; }
}

