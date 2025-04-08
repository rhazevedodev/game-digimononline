package br.com.digimon.infra.controller;

import br.com.digimon.app.dto.AuthRequestDTO;
import br.com.digimon.app.usecase.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
