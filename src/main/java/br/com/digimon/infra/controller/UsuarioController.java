package br.com.digimon.infra.controller;

import br.com.digimon.app.dto.CriarUsuarioDTO;
import br.com.digimon.app.dto.RespostaPadraoDTO;
import br.com.digimon.app.usecase.UsuarioUseCase;
import br.com.digimon.domain.port.in.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioUseCase usuarioUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> criarUsuario(@RequestBody @Valid CriarUsuarioDTO criarUsuarioDTO) {
        log.info("Recebida requisição para criar usuário");
        usuarioUseCase.criarUsuario(criarUsuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/primeiroAcesso/{usuario}")
    public ResponseEntity<?> verificarPrimeiroAcesso(@PathVariable String usuario) {
        log.info("Verificando se é o primeiro acesso do usuario: {}", usuario);
        return ResponseEntity.ok(usuarioUseCase.veriricarPrimeiroAcesso(usuario));
    }





}
