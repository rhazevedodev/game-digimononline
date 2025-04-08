-- Inserção de dados na tabela usuario
INSERT INTO usuario (
  nome_usuario,
  email,
  senha,
  data_nascimento,
  criado_por,
  criado_em,
  pontos_digitais,
  slots_digimon,
  indicacao,
  ultima_alteracao,
  ultima_alteracao_por
) VALUES
(
  'rafaeldev', -- Nome de usuário
  'rafael@email.com', -- Email do usuário
  'arisco2017', -- Senha criptografada
  '2000-01-01', -- Data de nascimento
  'admin', -- Criado por
  CURRENT_TIMESTAMP, -- Data de criação
  100, -- Pontos digitais
  3, -- Slots de Digimon
  NULL, -- Indicação
  CURRENT_TIMESTAMP, -- Última alteração
  'admin' -- Última alteração por
),
(
  'ana_dev', -- Nome de usuário
  'ana@email.com', -- Email do usuário
  '$2a$10$8xvYHdY9k4X.bGsE9z9EvuOz6FZGhRMSRHMtGAKnC7CebdcAPrlca', -- Senha criptografada
  '1998-05-20', -- Data de nascimento
  'admin', -- Criado por
  CURRENT_TIMESTAMP, -- Data de criação
  50, -- Pontos digitais
  2, -- Slots de Digimon
  'rafael@email.com', -- Indicação
  CURRENT_TIMESTAMP, -- Última alteração
  'rafael_test' -- Última alteração por
),
(
  'mario_gamer', -- Nome de usuário
  'mario@email.com', -- Email do usuário
  '$2a$10$8xvYHdY9k4X.bGsE9z9EvuOz6FZGhRMSRHMtGAKnC7CebdcAPrlca', -- Senha criptografada
  '1992-09-12', -- Data de nascimento
  'admin', -- Criado por
  CURRENT_TIMESTAMP, -- Data de criação
  200, -- Pontos digitais
  5, -- Slots de Digimon
  NULL, -- Indicação
  CURRENT_TIMESTAMP, -- Última alteração
  'ana_dev' -- Última alteração por
),
(
  'lucia_digimon', -- Nome de usuário
  'lucia@email.com', -- Email do usuário
  '$2a$10$8xvYHdY9k4X.bGsE9z9EvuOz6FZGhRMSRHMtGAKnC7CebdcAPrlca', -- Senha criptografada
  '2001-03-30', -- Data de nascimento
  'sistema', -- Criado por
  CURRENT_TIMESTAMP, -- Data de criação
  75, -- Pontos digitais
  2, -- Slots de Digimon
  'mario@email.com', -- Indicação
  CURRENT_TIMESTAMP, -- Última alteração
  'mario_gamer' -- Última alteração por
),
(
  'tester01', -- Nome de usuário
  'teste01@email.com', -- Email do usuário
  '$2a$10$8xvYHdY9k4X.bGsE9z9EvuOz6FZGhRMSRHMtGAKnC7CebdcAPrlca', -- Senha criptografada
  '1990-11-11', -- Data de nascimento
  'sistema', -- Criado por
  CURRENT_TIMESTAMP, -- Data de criação
  0, -- Pontos digitais
  1, -- Slots de Digimon
  NULL, -- Indicação
  CURRENT_TIMESTAMP, -- Última alteração
  'sistema' -- Última alteração por
);