package br.com.digimon.controller;

import br.com.digimon.service.CarregarTelaStatusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/telaStatus")
public class CarregarTelaStatusController {

    private CarregarTelaStatusService carregarTelaStatusService;

    public CarregarTelaStatusController(CarregarTelaStatusService carregarTelaStatusService) {
        this.carregarTelaStatusService = carregarTelaStatusService;
    }

    @GetMapping("/carregarTelaStatus/{idDigimon}")
    public ResponseEntity<?> carregarTelaStatus(@PathVariable Long idDigimon) {
        log.info("Requisição para carregar tela status recebida: {}", idDigimon);
        Map<String, Object> response = carregarTelaStatusService.carregarTelaStatus(idDigimon);
        log.info("Informações da tela status carregadas: {}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
