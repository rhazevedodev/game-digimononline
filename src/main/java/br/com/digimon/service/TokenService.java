package br.com.digimon.service;

import br.com.digimon.domain.TokenEntity;
import br.com.digimon.repository.TokenRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
public class TokenService {

    @Autowired
    private TokenRepository tokenRepositoryPort;

    public boolean validarToken(String token) {
        log.info("Validando token: {}", token);
        Optional<TokenEntity> tokenEntityOptional = tokenRepositoryPort.findByToken(token);
        if (tokenEntityOptional.isEmpty()) {
            return false;
        }
        TokenEntity tokenEntity = tokenEntityOptional.get();
        LocalDateTime expirationTime = tokenEntity.getExpirationTime();

        return expirationTime.isAfter(LocalDateTime.now());
    }

    public Optional<TokenEntity> verificarSeJaExisteTokenValido(String usuario) {
        Optional<TokenEntity> tokenExistente = tokenRepositoryPort.findTopByUsernameOrderByExpirationTimeDesc(usuario)
                .stream()
                .filter(token -> token.getExpirationTime().isAfter(LocalDateTime.now()))
                .findFirst();
        return tokenExistente;
    }

    public void criarToken(TokenEntity tokenEntity) {
        log.info("Criando novo token: {}", tokenEntity.getToken());
        tokenRepositoryPort.criarToken(tokenEntity);
    }

    public String obterUsuarioPorToken(String token) {
        log.info("Obtendo usuário por token: {}", token);
        Optional<TokenEntity> tokenEntityOptional = tokenRepositoryPort.findByToken(token);
        if (tokenEntityOptional.isPresent()) {
            return tokenEntityOptional.get().getUsername();
        }
        return null;
    }
}
