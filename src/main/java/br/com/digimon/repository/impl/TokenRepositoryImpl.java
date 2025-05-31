package br.com.digimon.repository.impl;

import br.com.digimon.domain.TokenEntity;
import br.com.digimon.repository.TokenRepository;
import br.com.digimon.repository.jpa.SpringDataTokenRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Slf4j
@Repository
public class TokenRepositoryImpl implements TokenRepository {

    @Autowired
    private SpringDataTokenRepository springDataTokenRepository;

    @Override
    public void criarToken(TokenEntity tokenEntity) {
        springDataTokenRepository.save(tokenEntity);
    }

    @Override
    public Optional<TokenEntity> findFirstByToken(String token) {
        return springDataTokenRepository.findFirstByToken(token);
    }

    @Override
    public Optional<TokenEntity> findByUsername(String usuario) {
        return springDataTokenRepository.findByUsername(usuario);
    }

    @Override
    public Optional<TokenEntity> findTopByUsernameOrderByExpirationTimeDesc(String usuario) {
        return springDataTokenRepository.findTopByUsernameOrderByExpirationTimeDesc(usuario);
    }
}
