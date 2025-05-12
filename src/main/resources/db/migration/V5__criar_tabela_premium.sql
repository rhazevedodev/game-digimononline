CREATE TABLE premium (
    id BIGSERIAL PRIMARY KEY,
    idDigimon BIGINT NOT NULL,
    totalDias INT NOT NULL,
    dataInicio TIMESTAMP NOT NULL,
    dataFim TIMESTAMP NOT NULL
);