CREATE TABLE token (
    id BIGSERIAL PRIMARY KEY,
    token VARCHAR(255) NOT NULL,
    expiration_time TIMESTAMP NOT NULL,
    username VARCHAR(255) NOT NULL
);