package br.com.digimon.infra.repository.impl;

import br.com.digimon.domain.entity.TokenEntity;
import br.com.digimon.domain.entity.UsuarioEntity;
import br.com.digimon.domain.port.out.TokenRepositoryPort;
import br.com.digimon.infra.repository.SpringDataTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class TokenRepositoryAdapter implements TokenRepositoryPort {

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
