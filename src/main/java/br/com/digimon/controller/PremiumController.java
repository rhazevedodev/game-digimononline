package br.com.digimon.controller;

import br.com.digimon.domain.dto.RequestAtivarPremiumDTO;
import br.com.digimon.service.PremiumService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
@Slf4j
@RestController
@RequestMapping("/api/premium")
public class PremiumController {

    private final PremiumService premiumService;

    public PremiumController(PremiumService premiumService) {
        this.premiumService = premiumService;
    }

    @PostMapping("/ativarPremium")
    public Map<String,Object> ativarPremium(@Valid @RequestBody RequestAtivarPremiumDTO request) {
        return premiumService.ativarPremium(request);
    }

    @GetMapping("/carregarInformacoesDeTelaPremium/{idDigimonUsuario}")
    public ResponseEntity<?> carregarInformacoesDeTelaPremium(@PathVariable int idDigimonUsuario) {
        log.info("Requisição para carregar informações de tela premium recebida: {}", idDigimonUsuario);
        Map<String, Object> response = premiumService.carregarInformacoesPremium(Long.valueOf(idDigimonUsuario));
        log.info("Informações de tela premium carregadas: {}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
