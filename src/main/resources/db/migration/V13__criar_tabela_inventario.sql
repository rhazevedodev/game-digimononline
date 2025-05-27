CREATE TABLE inventario (
    id SERIAL PRIMARY KEY,
    id_digimon BIGINT NOT NULL,
    id_item INT NOT NULL,
    nome_item VARCHAR(255) NOT NULL,
    descricao_item TEXT,
    quantidade INT NOT NULL,
    id_categoria INT NOT NULL,
    valor_compra INT NOT NULL,
    valor_venda INT NOT NULL,
    equipado BOOLEAN NOT NULL,
    pode_vender BOOLEAN NOT NULL,
    pode_trocar BOOLEAN NOT NULL,
    data_ultima_alteracao TIMESTAMP
);