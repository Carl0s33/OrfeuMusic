CREATE TABLE IF NOT EXISTS artista (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(200) NOT NULL,
    pais_origem VARCHAR(100),
    periodo_atividade VARCHAR(100),
    estilos VARCHAR(255)
);

