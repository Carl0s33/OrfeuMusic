CREATE TABLE faixas (
    id BIGSERIAL PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    duracao VARCHAR(20),
    album VARCHAR(255),
    creditos TEXT
);

