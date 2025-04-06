package br.com.digimon.infra.controller;

import br.com.digimon.app.dto.CriarUsuarioDTO;
import br.com.digimon.app.usecase.UsuarioUseCase;
import br.com.digimon.domain.port.in.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioUseCase usuarioUseCase;

    @PostMapping
    public ResponseEntity<Void> criarUsuario(@RequestBody CriarUsuarioDTO criarUsuarioDTO) {
        usuarioUseCase.criarUsuario(criarUsuarioDTO);
        return ResponseEntity.ok().build();
    }




}
