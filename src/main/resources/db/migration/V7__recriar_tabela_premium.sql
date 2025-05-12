CREATE TABLE premium (
    id BIGSERIAL PRIMARY KEY,
    id_digimon BIGINT NOT NULL,
    total_dias INT NOT NULL,
    data_inicio TIMESTAMP NOT NULL,
    data_fim TIMESTAMP NOT NULL
);