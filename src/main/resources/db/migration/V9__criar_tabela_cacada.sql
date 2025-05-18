CREATE TABLE cacada (
    id SERIAL PRIMARY KEY,
    id_digimon BIGINT,
    data DATE DEFAULT CURRENT_DATE,
    minutos INTEGER,
    hora_resgate_disponivel TIMESTAMP,
    recompensa_resgatada BOOLEAN,
    ultima_alteracao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);