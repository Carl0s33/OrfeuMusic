-- Senha para todos: admin123
-- Hash: $2a$10$XURPShQNCsLjp1ESc2laoObo9QZDhxz73hJPaEv7/cBha4pk0AgP.6

-- Admin
INSERT INTO usuarios (email, nome, senha, conta_ativa, ativo)
VALUES ('admin@orfeu.com', 'Admin User', '$2a$10$XURPShQNCsLjp1ESc2laoObo9QZDhxz73hJPaEv7/cBha4pk0AgP.6', TRUE, TRUE);

INSERT INTO usuario_roles (usuario_id, role)
SELECT id, 'ROLE_ADMIN' FROM usuarios WHERE email = 'admin@orfeu.com';

-- Editor
INSERT INTO usuarios (email, nome, senha, conta_ativa, ativo)
VALUES ('editor@orfeu.com', 'Editor User', '$2a$10$XURPShQNCsLjp1ESc2laoObo9QZDhxz73hJPaEv7/cBha4pk0AgP.6', TRUE, TRUE);

INSERT INTO usuario_roles (usuario_id, role)
SELECT id, 'ROLE_EDITOR' FROM usuarios WHERE email = 'editor@orfeu.com';

-- Colaborador
INSERT INTO usuarios (email, nome, senha, conta_ativa, ativo)
VALUES ('colaborador@orfeu.com', 'Colaborador User', '$2a$10$XURPShQNCsLjp1ESc2laoObo9QZDhxz73hJPaEv7/cBha4pk0AgP.6', TRUE, TRUE);

INSERT INTO usuario_roles (usuario_id, role)
SELECT id, 'ROLE_COLABORADOR' FROM usuarios WHERE email = 'colaborador@orfeu.com';

