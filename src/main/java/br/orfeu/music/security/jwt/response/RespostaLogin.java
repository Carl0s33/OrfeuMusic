package br.orfeu.music.security.jwt.response;

import java.util.List;

public class RespostaLogin {
    private String token;
    private String tipo = "Bearer";
    private Long id;
    private String email;
    private String nome;
    private String fotoUrl;
    private List<String> permissoes;

    public RespostaLogin(String token, Long id, String email, String nome, String fotoUrl, List<String> permissoes) {
        this.token = token;
        this.id = id;
        this.email = email;
        this.nome = nome;
        this.fotoUrl = fotoUrl;
        this.permissoes = permissoes;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }

    public List<String> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(List<String> permissoes) {
        this.permissoes = permissoes;
    }
}
