package br.com.digimon.controller;

import br.com.digimon.domain.LogEntity;
import br.com.digimon.domain.dto.*;
import br.com.digimon.domain.fromJson.ListaDigitamasJson;
import br.com.digimon.service.*;
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
    private SelecionarDigitamaService selecionarDigitamaService;

    @Autowired
    private DigimonService digimonService;

    @Autowired
    private ChocarDigitamaService chocarDigitamaService;

    @Autowired
    private LogService logService;

    @PostMapping("/selecionar")
    public ResponseEntity<?> selecionarDigimon(@Valid @RequestBody RequestSelecaoDigimonDTO requestSelecaoDigimonDTO, HttpServletRequest request) {
        log.info("Iniciando seleção de Digimon: {}", requestSelecaoDigimonDTO.getApelidoDigimon());
        ResponseEntity<?> response = selecionarDigimonService.selecionarDigimon(requestSelecaoDigimonDTO, request);

        LogEntity logEntity = new LogEntity();
        logEntity.setAcao("SUCESSO SELEÇÃO DIGIMON");
        logEntity.setDetalhes("Seleção de Digimon realizada com sucesso para o apelido: " + requestSelecaoDigimonDTO.getApelidoDigimon());
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

    @GetMapping("/carregarDigitamas")
    public ResponseEntity<?> carregarDigitamas() {
        log.info("Requisição para carregar digitamas recebida");
        ListaDigitamasJson response = digimonService.getDigitamasByJson();
        log.info("Digitamas carregadas: {}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/selecionarDigitama")
    public ResponseEntity<?> selecionarDigitama(@RequestBody RequestSelecaoDigitamaDTO requestSelecaoDigitamaDTO, HttpServletRequest request) {
        log.info("Requisição para selecionar digitama recebida");
        ResponseSelecaoDigitamaDTO response = selecionarDigitamaService.selecionarDigitama(requestSelecaoDigitamaDTO,request);
        log.info("Digitama selecionada: {}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/chocarDigitama/{idDigitama}")
    public ResponseEntity<?> chocarDigitama(@PathVariable String idDigitama, HttpServletRequest request) {
        log.info("Requisição para chocar digitama recebida");
        ResponseChocarDigitamaDTO response = chocarDigitamaService.chocarDigitama(idDigitama,request);
        log.info("Digitama chocada: {}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
