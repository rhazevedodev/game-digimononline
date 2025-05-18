CREATE TABLE tempo_disponivel_cacada (
    id SERIAL PRIMARY KEY,
    id_digimon BIGINT NOT NULL,
    data_cadastro DATE DEFAULT CURRENT_DATE,
    tempo_disponivel INTEGER,
    data_ultima_alteracao TIMESTAMP
);
