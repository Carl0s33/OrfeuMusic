package br.orfeu.music.security.services;

import br.orfeu.music.model.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Builder
public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private final Long id;
    private final String username;
    private final String email;
    private final String nome;
    private final String fotoUrl;

    @JsonIgnore
    private final String password;

    @Builder.Default
    private final Collection<? extends GrantedAuthority> authorities = List.of();

    @Builder.Default
    private final boolean ativo = true;

    public UserDetailsImpl(Long id, String username, String email, String nome, String fotoUrl, String password,
                         Collection<? extends GrantedAuthority> authorities, boolean ativo) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.nome = nome;
        this.fotoUrl = fotoUrl;
        this.password = password;
        this.authorities = authorities != null ? authorities : List.of();
        this.ativo = ativo;
    }

    public static UserDetailsImpl build(Usuario usuario) {
        List<GrantedAuthority> authorities = usuario.getAuthorities().stream()
                .map(role -> new SimpleGrantedAuthority(role.getAuthority()))
                .collect(Collectors.toList());

        return UserDetailsImpl.builder()
                .id(usuario.getId())
                .username(usuario.getEmail())
                .email(usuario.getEmail())
                .nome(usuario.getNome())
                .fotoUrl(usuario.getFotoUrl())
                .password(usuario.getSenha())
                .authorities(authorities)
                .ativo(usuario.isAtivo())
                .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return ativo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
