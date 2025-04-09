package br.com.digimon.controller;


import br.com.digimon.domain.dto.ValidarTokenDTO;
import br.com.digimon.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/token")
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @PostMapping("/validarToken")
    public ResponseEntity<String> validarToken(@RequestBody ValidarTokenDTO token) {
        log.info("Validando token: {}", token.getToken());
        boolean isValid = tokenService.validarToken(token.getToken());
        if (isValid) {
            return ResponseEntity.ok("Token válido");
        } else {
            return ResponseEntity.status(401).body("Token inválido");
        }
    }
}
