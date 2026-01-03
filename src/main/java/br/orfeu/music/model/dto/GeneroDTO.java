package br.orfeu.music.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class GeneroDTO {

    private Long id;

    @NotBlank
    @Size(max = 200)
    private String nome;

    @Size(max = 500)
    private String descricao;

    @Size(max = 20)
    private String status;

    private Integer numeroArtistas;
    private Integer numeroLancamentos;
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

