package br.com.digimon.controller;


import br.com.digimon.domain.dto.CriarUsuarioDTO;
import br.com.digimon.service.ContinuarJornadaService;
import br.com.digimon.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private ContinuarJornadaService continuarJornadaService;

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

    @GetMapping("/primeiroAcesso/{usuario}")
    public ResponseEntity<?> verificarPrimeiroAcesso(@PathVariable String usuario) {
        log.info("Verificando se é o primeiro acesso do usuario: {}", usuario);
        return ResponseEntity.ok(usuarioService.verificarPrimeiroAcesso(usuario));
    }

    @GetMapping("/carregarPontosDigitais")
    public ResponseEntity<?> carregarPontosDigitais(HttpServletRequest request) {
        log.info("Carregando pontos digitais do usuario: ");
        return ResponseEntity.ok(usuarioService.carregarPontosDigitais(request));
    }

    @GetMapping("/validarSlotsDigimon")
    public ResponseEntity<?> validarSlotsDigimon(HttpServletRequest request) {
        log.info("Validando slots de digimon do usuario: ");
        return ResponseEntity.ok(continuarJornadaService.validarSlotsDigimon(request));
    }

    @GetMapping("/obterDigimons")
    public ResponseEntity<?> obterDigimons(HttpServletRequest request) {
        log.info("Carregando digimons do usuario: ");
        return ResponseEntity.ok(continuarJornadaService.carregarDigimonsContinuarJornada(request));
    }






}
