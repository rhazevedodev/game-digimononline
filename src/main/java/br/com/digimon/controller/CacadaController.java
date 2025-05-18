package br.com.digimon.controller;

import br.com.digimon.domain.dto.RequestCarregarTelaCacadasDTO;
import br.com.digimon.service.CacadaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/cacada")
public class CacadaController {

    @Autowired
    private CacadaService cacadaService;

    @GetMapping("/carregar/{idDigimon}")
    public Map<String,Object> carregaTelaCacadas(@PathVariable Long idDigimon) {
        return cacadaService.carregarTelaCacada(idDigimon);
    }
}
