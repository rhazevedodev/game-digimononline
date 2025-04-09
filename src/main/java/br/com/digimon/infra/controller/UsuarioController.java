package br.com.digimon.infra.controller;

import br.com.digimon.app.dto.CriarUsuarioDTO;
import br.com.digimon.app.service.UsuarioService;
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
    private UsuarioService usuarioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    //@Operation(summary = "Criar um novo usuario")
    //@ApiResponses(value = {
    //        @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
    //        @ApiResponse(responseCode = "409", description = "Usuário ou Email já cadastrados no sistema")
    //})
    public ResponseEntity<Void> criarUsuario(@RequestBody @Valid CriarUsuarioDTO criarUsuarioDTO) {
        log.info("Recebida requisição para criar usuário");
        usuarioService.criarUsuario(criarUsuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //@Operation(summary = "Verificar primeiro acesso")
    //@ApiResponses(value = {
    //        @ApiResponse(responseCode = "200", description = "Usuario existe na base de dados e foi validado"),
    //        @ApiResponse(responseCode = "404", description = "Usuário não existe")
    //})
    @GetMapping("/primeiroAcesso/{usuario}")
    public ResponseEntity<?> verificarPrimeiroAcesso(@PathVariable String usuario) {
        log.info("Verificando se é o primeiro acesso do usuario: {}", usuario);
        return ResponseEntity.ok(usuarioService.veriricarPrimeiroAcesso(usuario));
    }





}
