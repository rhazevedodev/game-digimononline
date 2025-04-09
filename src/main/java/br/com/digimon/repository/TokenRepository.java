package br.com.digimon.repository;

import br.com.digimon.domain.TokenEntity;

import java.util.Optional;

public interface TokenRepository {

    void criarToken(TokenEntity tokenEntity);

    Optional<TokenEntity> findByToken(String token);

    Optional<TokenEntity> findByUsername(String usuario);

    Optional<TokenEntity> findTopByUsernameOrderByExpirationTimeDesc(String usuario);
}
