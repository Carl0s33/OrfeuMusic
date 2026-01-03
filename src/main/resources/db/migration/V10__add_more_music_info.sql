-- Add gravadora and genero_id to album table
ALTER TABLE album
    ADD COLUMN gravadora VARCHAR(255),
    ADD COLUMN genero_id BIGINT;

ALTER TABLE album
    ADD CONSTRAINT fk_album_genero FOREIGN KEY (genero_id) REFERENCES genero (id) ON DELETE SET NULL;

-- Add letra to faixas table
ALTER TABLE faixas
    ADD COLUMN letra TEXT;

