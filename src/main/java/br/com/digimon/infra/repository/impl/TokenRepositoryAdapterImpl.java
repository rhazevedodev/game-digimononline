package br.com.digimon.infra.repository.impl;

import br.com.digimon.domain.entity.TokenEntity;
import br.com.digimon.domain.port.out.TokenRepositoryPort;
import br.com.digimon.infra.repository.jpa.SpringDataTokenRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Slf4j
@Repository
public class TokenRepositoryAdapterImpl implements TokenRepositoryPort {

    @Autowired
    private SpringDataTokenRepository springDataTokenRepository;

    @Override
    public void criarToken(TokenEntity tokenEntity) {
        springDataTokenRepository.save(tokenEntity);
    }

    @Override
    public Optional<TokenEntity> findByToken(String token) {
        Optional<TokenEntity> tokenEntity = springDataTokenRepository.findByToken(token);
        return tokenEntity;
    }

    @Override
    public Optional<TokenEntity> findByUsername(String usuario) {
        return springDataTokenRepository.findByUsername(usuario);
    }
}
