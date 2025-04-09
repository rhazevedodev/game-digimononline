package br.com.digimon.infra.controller;

import br.com.digimon.app.dto.AuthRequestDTO;
import br.com.digimon.app.dto.ValidarTokenDTO;
import br.com.digimon.app.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequestDTO authRequest) {
        String token = authService.login(authRequest.getUsuario(), authRequest.getSenha());
        return ResponseEntity.ok(token);
    }
}
