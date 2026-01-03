package br.orfeu.music.repository.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "genero")
public class GeneroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String nome;

    @Column(length = 500)
    private String descricao;

    @Column(length = 20)
    private String status;

    @Column(name = "numero_artistas")
    private Integer numeroArtistas;

    @Column(name = "numero_lancamentos")
    private Integer numeroLancamentos;

    @Column(name = "numero_faixas")
    private Integer numeroFaixas;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Integer getNumeroArtistas() { return numeroArtistas; }
    public void setNumeroArtistas(Integer numeroArtistas) { this.numeroArtistas = numeroArtistas; }

    public Integer getNumeroLancamentos() { return numeroLancamentos; }
    public void setNumeroLancamentos(Integer numeroLancamentos) { this.numeroLancamentos = numeroLancamentos; }

    public Integer getNumeroFaixas() { return numeroFaixas; }
    public void setNumeroFaixas(Integer numeroFaixas) { this.numeroFaixas = numeroFaixas; }
}

