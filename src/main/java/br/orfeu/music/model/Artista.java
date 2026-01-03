package br.orfeu.music.model;

import java.time.LocalDate;

public class Artista {

    private Long id;
    private String nome;
    private String nomeVerdadeiro;
    private LocalDate dataNascimento;
    private String fotoUrl;
    private String biografia;
    private String paisOrigem;
    private String periodoAtividade;
    private String estilos;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getNomeVerdadeiro() { return nomeVerdadeiro; }
    public void setNomeVerdadeiro(String nomeVerdadeiro) { this.nomeVerdadeiro = nomeVerdadeiro; }

    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }

    public String getFotoUrl() { return fotoUrl; }
    public void setFotoUrl(String fotoUrl) { this.fotoUrl = fotoUrl; }

    public String getBiografia() { return biografia; }
    public void setBiografia(String biografia) { this.biografia = biografia; }

    public String getPaisOrigem() { return paisOrigem; }
    public void setPaisOrigem(String paisOrigem) { this.paisOrigem = paisOrigem; }

    public String getPeriodoAtividade() { return periodoAtividade; }
    public void setPeriodoAtividade(String periodoAtividade) { this.periodoAtividade = periodoAtividade; }

    public String getEstilos() { return estilos; }
    public void setEstilos(String estilos) { this.estilos = estilos; }
}
