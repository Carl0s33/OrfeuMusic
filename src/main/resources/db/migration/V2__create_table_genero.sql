CREATE TABLE genero (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(200) NOT NULL,
    descricao VARCHAR(500),
    status VARCHAR(20),
    numero_artistas INTEGER,
    numero_lancamentos INTEGER,
    numero_faixas INTEGER
);

