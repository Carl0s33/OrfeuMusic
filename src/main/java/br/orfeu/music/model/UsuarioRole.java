package br.orfeu.music.model;

import lombok.Getter;

@Getter
public enum UsuarioRole {
    ROLE_VISITANTE("Visitante"),
    ROLE_USER("Usuário"),
    ROLE_COLABORADOR("Colaborador"),
    ROLE_EDITOR("Editor"),
    ROLE_ADMIN("Administrador");

    private final String descricao;

    UsuarioRole(String descricao) {
        this.descricao = descricao;
    }

    public String getRole() {
        return this.name();
    }
}
