package br.com.digimon.controller;

import br.com.digimon.domain.LogEntity;
import br.com.digimon.domain.dto.AuthRequestDTO;
import br.com.digimon.domain.dto.ResponsePadraoDTO;
import br.com.digimon.service.AuthService;
import br.com.digimon.service.LogService;
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

    @Autowired
    private LogService logService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequestDTO authRequest) {
        try {
            String token = authService.login(authRequest.getUsuario(), authRequest.getSenha());

            LogEntity logEntity = new LogEntity();
            logEntity.setAcao("SUCESSO LOGIN");
            logEntity.setDetalhes("Login realizado com sucesso para o usuário: " + authRequest.getUsuario() + ", Token: " + token);

            logService.saveLog(logEntity);
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            LogEntity logEntity = new LogEntity();
            logEntity.setAcao("FALHA LOGIN");
            logEntity.setDetalhes("Falha ao realizar login para o usuário: " + authRequest.getUsuario() + ", Erro: " + e.getMessage());

            logService.saveLog(logEntity);
            return ResponseEntity.status(401).body(new ResponsePadraoDTO("Login falhou: " + e.getMessage()));
        }
    }
}
