package br.com.digimon.domain.port.in;

import br.com.digimon.domain.entity.TokenEntity;

import java.util.Optional;

public interface Token {
    boolean validarToken(String token);
    Optional<TokenEntity> verificarSeJaExisteTokenValido(String usuario);
    void criarToken(TokenEntity tokenEntity);
    String obterUsuarioPorToken(String token);
}
