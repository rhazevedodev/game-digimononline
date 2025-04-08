package br.com.digimon.app.usecase;

import br.com.digimon.domain.entity.TokenEntity;
import br.com.digimon.domain.port.out.TokenRepository;
import br.com.digimon.shared.exception.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.Date;

@Slf4j
@Service
public class AuthService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    public String login(String usuario, String senha) {
        log.info("Iniciando processo de login para o usuário: {}", usuario);

        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(usuario, senha);

        Authentication authentication = authenticationManager.authenticate(authToken); // <-- isso valida a senha com BCrypt automaticamente

        String jwt = jwtUtil.generateToken(usuario);
        Date expiration = jwtUtil.getExpirationDateFromToken(jwt);

        TokenEntity tokenEntity = new TokenEntity();
        tokenEntity.setToken(jwt);
        tokenEntity.setUsername(usuario);
        tokenEntity.setExpirationTime(expiration.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());

        tokenRepository.save(tokenEntity);

        return jwt;
    }

    public boolean isTokenValid(String token) {
        return jwtUtil.isTokenValid(token) &&
                tokenRepository.findByToken(token).isPresent();
    }
}
