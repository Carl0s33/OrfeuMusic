package br.orfeu.music.model;

public class Genero {

    private Long id;
    private String nome;
    private String descricao;
    private String status; // aprovado, pendente, rejeitado
    private Integer numeroFaixas;
    private Integer numeroLancamentos;
    private Integer numeroArtistas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getNumeroFaixas() {
        return numeroFaixas;
    }

    public void setNumeroFaixas(Integer numeroFaixas) {
        this.numeroFaixas = numeroFaixas;
    }

    public Integer getNumeroLancamentos() {
        return numeroLancamentos;
    }

    public void setNumeroLancamentos(Integer numeroLancamentos) {
        this.numeroLancamentos = numeroLancamentos;
    }

    public Integer getNumeroArtistas() {
        return numeroArtistas;
    }

    public void setNumeroArtistas(Integer numeroArtistas) {
        this.numeroArtistas = numeroArtistas;
    }
}
