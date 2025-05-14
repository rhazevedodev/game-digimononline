package br.com.digimon.service;


import br.com.digimon.domain.LogEntity;
import br.com.digimon.domain.TokenEntity;
import br.com.digimon.utils.JwtUtil;
import com.google.gson.internal.NonNullElementWrapperList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginContext;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Slf4j
@Service
public class AuthService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    public String login(String usuario, String senha) {
        log.info("Iniciando processo de login para o usuário: {}", usuario);

        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(usuario, senha);

        Authentication authentication = authenticationManager.authenticate(authToken); // <-- isso valida a senha com BCrypt automaticamente

        // 1. Verifica se já existe um token válido
        Optional<TokenEntity> tokenExistente = tokenService.verificarSeJaExisteTokenValido(usuario);

        if (tokenExistente.isPresent()) {
            log.info("Token já existente e válido encontrado. Reutilizando.");
            return tokenExistente.get().getToken();
        }

        // 2. Caso contrário, gera um novo token
        String jwt = jwtUtil.generateToken(usuario);
        Date expiration = jwtUtil.getExpirationDateFromToken(jwt);

        TokenEntity tokenEntity = new TokenEntity();
        tokenEntity.setToken(jwt);
        tokenEntity.setUsername(usuario);
        tokenEntity.setExpirationTime(expiration.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());

        tokenService.criarToken(tokenEntity);
        return jwt;
    }
}
