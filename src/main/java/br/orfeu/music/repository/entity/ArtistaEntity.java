package br.orfeu.music.repository.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "artista")
public class ArtistaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String nome;

    @Column(name = "nome_verdadeiro")
    private String nomeVerdadeiro;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "foto_url", length = 500)
    private String fotoUrl;

    @Column(columnDefinition = "TEXT")
    private String biografia;

    @Column(name = "pais_origem", length = 100)
    private String paisOrigem;

    @Column(name = "periodo_atividade", length = 100)
    private String periodoAtividade;

    @Column(length = 255)
    private String estilos;

    @Column(length = 20)
    private String status = "PENDING";

    @OneToMany(mappedBy = "artista", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private java.util.List<AlbumEntity> albuns;

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

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public java.util.List<AlbumEntity> getAlbuns() { return albuns; }
    public void setAlbuns(java.util.List<AlbumEntity> albuns) { this.albuns = albuns; }
}
