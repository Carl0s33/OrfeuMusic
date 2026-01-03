-- Migration renomeada de V1__Create_User_Tables.sql para V3__Create_User_Tables.sql para evitar conflito de versão com V1__create_table_artista.sql

-- Criação da tabela de usuários
CREATE TABLE IF NOT EXISTS usuarios (
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    nome VARCHAR(255) NOT NULL,
    senha VARCHAR(255) NOT NULL,
    ativo BOOLEAN DEFAULT TRUE,
    data_criacao TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Tabela para armazenar os papéis (roles) dos usuários
CREATE TABLE IF NOT EXISTS usuario_roles (
    usuario_id BIGINT NOT NULL,
    role VARCHAR(50) NOT NULL,
    PRIMARY KEY (usuario_id, role),
    CONSTRAINT fk_usuario_roles_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios (id) ON DELETE CASCADE
);

-- Tabela para armazenar tokens de redefinição de senha
CREATE TABLE IF NOT EXISTS reset_tokens (
    id BIGSERIAL PRIMARY KEY,
    token VARCHAR(255) NOT NULL UNIQUE,
    usuario_id BIGINT NOT NULL,
    data_expiracao TIMESTAMP WITH TIME ZONE NOT NULL,
    utilizado BOOLEAN DEFAULT FALSE,
    data_criacao TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_reset_tokens_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios (id) ON DELETE CASCADE
);

-- Índices para melhorar o desempenho das consultas
CREATE INDEX IF NOT EXISTS idx_usuarios_email ON usuarios (email);
CREATE INDEX IF NOT EXISTS idx_usuarios_ativo ON usuarios (ativo);
CREATE INDEX IF NOT EXISTS idx_usuario_roles_usuario_id ON usuario_roles (usuario_id);
CREATE INDEX IF NOT EXISTS idx_reset_tokens_token ON reset_tokens (token);
CREATE INDEX IF NOT EXISTS idx_reset_tokens_usuario_id ON reset_tokens (usuario_id);

-- Inserir usuário administrador padrão (senha: admin123)
-- A senha será criptografada pelo serviço de usuário
INSERT INTO usuarios (email, nome, senha, ativo)
SELECT 'admin@orfeumusic.com', 'Administrador', '$2a$10$XURPShQNCsLjp1ESc2laoObo9QZDhxz73hJPaEv7/cBha4pk0AgP.6', TRUE
WHERE NOT EXISTS (SELECT 1 FROM usuarios WHERE email = 'admin@orfeumusic.com');

-- Atribuir papéis ao usuário administrador
INSERT INTO usuario_roles (usuario_id, role)
SELECT u.id, 'ROLE_ADMIN' 
FROM usuarios u 
WHERE u.email = 'admin@orfeumusic.com' 
AND NOT EXISTS (SELECT 1 FROM usuario_roles WHERE usuario_id = u.id AND role = 'ROLE_ADMIN');

INSERT INTO usuario_roles (usuario_id, role)
SELECT u.id, 'ROLE_EDITOR' 
FROM usuarios u 
WHERE u.email = 'admin@orfeumusic.com' 
AND NOT EXISTS (SELECT 1 FROM usuario_roles WHERE usuario_id = u.id AND role = 'ROLE_EDITOR');

INSERT INTO usuario_roles (usuario_id, role)
SELECT u.id, 'ROLE_COLABORADOR' 
FROM usuarios u 
WHERE u.email = 'admin@orfeumusic.com' 
AND NOT EXISTS (SELECT 1 FROM usuario_roles WHERE usuario_id = u.id AND role = 'ROLE_COLABORADOR');

-- Comentário sobre a senha padrão
COMMENT ON TABLE usuarios IS 'Tabela de usuários do sistema Orfeu Music';
COMMENT ON COLUMN usuarios.senha IS 'Senha criptografada usando BCrypt';
COMMENT ON TABLE usuario_roles IS 'Tabela de relacionamento entre usuários e seus papéis';
COMMENT ON TABLE reset_tokens IS 'Tabela para armazenar tokens de redefinição de senha';
