CREATE TABLE digimon (
    id SERIAL PRIMARY KEY,
    id_jogador BIGINT NOT NULL,

    id_rookie INTEGER,
    tier_rookie INTEGER,
    id_champion INTEGER,
    tier_champion INTEGER,
    id_ultimate INTEGER,
    tier_ultimate INTEGER,
    id_mega INTEGER,
    tier_mega INTEGER,

    nome VARCHAR(20) NOT NULL CHECK (LENGTH(nome) BETWEEN 4 AND 20),

    nivel INTEGER DEFAULT 1,
    bits INTEGER DEFAULT 100,
    diamantes INTEGER,
    pontos_experiencia INTEGER,

    -- Atributos
    pontos_vida INTEGER DEFAULT 0,
    pontos_energia INTEGER DEFAULT 0,
    pontos_forca INTEGER DEFAULT 0,
    pontos_inteligencia INTEGER DEFAULT 0,
    pontos_agilidade INTEGER DEFAULT 0,
    pontos_conhecimento INTEGER DEFAULT 0,
    pontos_resistencia INTEGER DEFAULT 0,

    -- Atributos Base
    base_vida INTEGER DEFAULT 0,
    base_energia INTEGER DEFAULT 0,
    base_forca INTEGER DEFAULT 0,
    base_inteligencia INTEGER DEFAULT 0,
    base_agilidade INTEGER DEFAULT 0,
    base_conhecimento INTEGER DEFAULT 0,
    base_resistencia INTEGER DEFAULT 0,

    -- Atributos Manipuláveis
    manipulavel_vida INTEGER DEFAULT 0,
    manipulavel_energia INTEGER DEFAULT 0,
    manipulavel_forca INTEGER DEFAULT 0,
    manipulavel_inteligencia INTEGER DEFAULT 0,
    manipulavel_agilidade INTEGER DEFAULT 0,
    manipulavel_conhecimento INTEGER DEFAULT 0,
    manipulavel_resistencia INTEGER DEFAULT 0,

    -- Atributos Modificadores
    modificador_vida INTEGER DEFAULT 0,
    modificador_energia INTEGER DEFAULT 0,
    modificador_forca INTEGER DEFAULT 0,
    modificador_inteligencia INTEGER DEFAULT 0,
    modificador_agilidade INTEGER DEFAULT 0,
    modificador_conhecimento INTEGER DEFAULT 0,
    modificador_resistencia INTEGER DEFAULT 0,

    -- Elementos
    elemento_primitivo INTEGER,
    pontos_elemento_primitivo INTEGER,

    data_cadastro DATE DEFAULT CURRENT_DATE,
    data_ultima_alteracao TIMESTAMP,

    bonus_experiencia_ativo BOOLEAN DEFAULT FALSE,
    bonus_bits_ativo BOOLEAN DEFAULT FALSE,
    sacrificado BOOLEAN DEFAULT FALSE
);
