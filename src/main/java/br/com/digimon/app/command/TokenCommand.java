package br.com.digimon.app.command;

import br.com.digimon.app.service.TokenService;
import br.com.digimon.domain.entity.TokenEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

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
