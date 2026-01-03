package br.orfeu.music.model.dto;

import br.orfeu.music.model.UsuarioRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class UsuarioDTO {
    private Long id;
    
    @NotBlank(message = "O nome é obrigatório")
    private String nome;
    
    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "E-mail inválido")
    private String email;
    
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
    private String senha;

    private String fotoUrl;

    private boolean ativo;
    private Set<UsuarioRole> roles;
    
    // Campos para o formulário de cadastro
    private boolean colaborador;
    private boolean editor;
    
    // Método auxiliar para verificar se o usuário é administrador
    public boolean isAdmin() {
        return roles != null && roles.contains(UsuarioRole.ROLE_ADMIN);
    }
}
