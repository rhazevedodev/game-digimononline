CREATE TABLE usuario (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome_usuario VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    data_nascimento DATE,
    criado_por VARCHAR(50),
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ultima_alteracao TIMESTAMP,
    ultima_alteracao_por VARCHAR(50),
    pontos_digitais INT DEFAULT 0,
    slots_digimon INT DEFAULT 1,
    indicacao VARCHAR(100)
);