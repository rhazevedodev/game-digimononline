package br.com.digimon.controller;

import br.com.digimon.domain.dto.SelecaoDigimonDTO;
import br.com.digimon.service.DigimonService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/digimon")
public class DigimonController {

    @Autowired
    private DigimonService digimonService;

    @PostMapping("/selecionar")
    public ResponseEntity<?> selecionarDigimon(@RequestBody SelecaoDigimonDTO selecaoDigimonDTO, HttpServletRequest request) {
        log.info("Iniciando seleção de Digimon : {}",selecaoDigimonDTO.getApelidoDigimon());
       ;
        return ResponseEntity.ok(digimonService.selecionarDigimon(selecaoDigimonDTO, request));
    }
}
