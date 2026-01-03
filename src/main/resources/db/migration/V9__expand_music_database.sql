-- Add new columns to artista table
ALTER TABLE artista
    ADD COLUMN nome_verdadeiro VARCHAR(255),
    ADD COLUMN data_nascimento DATE,
    ADD COLUMN foto_url VARCHAR(500),
    ADD COLUMN biografia TEXT;

-- Create album table
CREATE TABLE album (
    id BIGSERIAL PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    data_lancamento DATE,
    capa_url VARCHAR(500),
    tipo VARCHAR(50), -- Album, EP, Single
    artista_id BIGINT,
    CONSTRAINT fk_album_artista FOREIGN KEY (artista_id) REFERENCES artista (id) ON DELETE SET NULL
);

-- Update faixas table to link to album
ALTER TABLE faixas
    ADD COLUMN album_id BIGINT,
    ADD COLUMN numero_faixa INTEGER,
    ADD COLUMN compositores VARCHAR(500),
    ADD COLUMN produtores VARCHAR(500);

ALTER TABLE faixas
    ADD CONSTRAINT fk_faixas_album FOREIGN KEY (album_id) REFERENCES album (id) ON DELETE SET NULL;

