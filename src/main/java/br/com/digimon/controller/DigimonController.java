package br.com.digimon.controller;

import br.com.digimon.domain.dto.SelecaoDigimonDTO;
import br.com.digimon.service.DigimonService;
import br.com.digimon.service.SelecionarDigimonService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/digimon")
public class DigimonController {

    @Autowired
    private SelecionarDigimonService selecionarDigimonService;

    @PostMapping("/selecionar")
    public ResponseEntity<?> selecionarDigimon(@Valid @RequestBody SelecaoDigimonDTO selecaoDigimonDTO, HttpServletRequest request) {
        log.info("Iniciando seleção de Digimon : {}",selecaoDigimonDTO.getApelidoDigimon());
       ;
        return ResponseEntity.ok(selecionarDigimonService.selecionarDigimon(selecaoDigimonDTO, request));
    }


}
