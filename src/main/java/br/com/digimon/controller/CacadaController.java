package br.com.digimon.controller;

import br.com.digimon.domain.dto.RequestCarregarTelaCacadasDTO;
import br.com.digimon.domain.dto.RequestIniciarCacadaDTO;
import br.com.digimon.service.CacadaService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/cacada")
public class CacadaController {

    @Autowired
    private CacadaService cacadaService;

    @GetMapping("/carregar/{idDigimon}")
    public ResponseEntity<?> carregaTelaCacadas(@PathVariable Long idDigimon, HttpServletRequest request) {
        return cacadaService.carregarCacadasJogador(idDigimon,request);
    }

    @PostMapping("/iniciarCacada")
    public ResponseEntity<?> iniciarCacada(@RequestBody RequestIniciarCacadaDTO request, HttpServletRequest httpServletRequest) {
        return cacadaService.iniciarCacada(request.getIdDigimon(), request.getCacadaSelecionada());
    }
}
