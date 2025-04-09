package br.com.digimon.service.command;

import br.com.digimon.domain.TokenEntity;
import br.com.digimon.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TokenCommand {

    @Autowired
    private TokenService tokenService;

    public Optional<TokenEntity> verificarSeJaExisteTokenValido(String usuario) {
        return tokenService.verificarSeJaExisteTokenValido(usuario);
    }

    public void criarToken(TokenEntity tokenEntity) {
        tokenService.criarToken(tokenEntity);
    }

    public String obterUsuarioPorToken(String token) {
        return tokenService.obterUsuarioPorToken(token);
    }
}
