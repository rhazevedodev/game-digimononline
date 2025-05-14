package br.com.digimon.controller;

import br.com.digimon.domain.LogEntity;
import br.com.digimon.domain.dto.RequestCarregarTelaStatusDTO;
import br.com.digimon.domain.dto.RespostaPadraoDTO;
import br.com.digimon.domain.dto.SelecaoDigimonDTO;
import br.com.digimon.service.DigimonService;
import br.com.digimon.service.LogService;
import br.com.digimon.service.SelecionarDigimonService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/digimon")
public class DigimonController {

    @Autowired
    private SelecionarDigimonService selecionarDigimonService;

    @Autowired
    private DigimonService digimonService;

    @Autowired
    private LogService logService;

    @PostMapping("/selecionar")
    public ResponseEntity<?> selecionarDigimon(@Valid @RequestBody SelecaoDigimonDTO selecaoDigimonDTO, HttpServletRequest request) {
            log.info("Iniciando seleção de Digimon: {}", selecaoDigimonDTO.getApelidoDigimon());
            ResponseEntity<?> response = selecionarDigimonService.selecionarDigimon(selecaoDigimonDTO, request);

            LogEntity logEntity = new LogEntity();
            logEntity.setAcao("SUCESSO SELEÇÃO DIGIMON");
            logEntity.setDetalhes("Seleção de Digimon realizada com sucesso para o apelido: " + selecaoDigimonDTO.getApelidoDigimon());
            logService.saveLog(logEntity);

            return response;
    }

    @GetMapping("/carregarVidaEnergia/{idDigimonUsuario}")
    public ResponseEntity<?> carregarVidaEnergia(@PathVariable int idDigimonUsuario) {
        log.info("Requisição para carregar vida e energia do digimon recebida: {}", idDigimonUsuario);
        Map<String, Object> response = digimonService.carregarVidaEnergia(Long.valueOf(idDigimonUsuario));
        log.info("Vida e energia do digimon carregadas: {}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }




}
