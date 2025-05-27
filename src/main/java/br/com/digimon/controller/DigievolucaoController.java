package br.com.digimon.controller;

import br.com.digimon.domain.dto.ResponseListarDigievolucoes;
import br.com.digimon.service.DigievolucaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/digievolucao")
public class DigievolucaoController {

    @Autowired
    private DigievolucaoService digievolucaoService;

    @GetMapping("/{idDigimonUsuario}")
    public ResponseEntity<List<ResponseListarDigievolucoes>> listarEvolucoes(@PathVariable int idDigimonUsuario) {
        List<ResponseListarDigievolucoes> evolucoes = digievolucaoService.getEvolucoes(idDigimonUsuario);
        return ResponseEntity.ok(evolucoes);
    }

    /*
    @PostMapping("/digievoluir")
    public ResponseEntity<String> digievolucao(@RequestBody RequestDigievolucao request) {
        digievolucaoService.digievolucao(request);
        return ResponseEntity.ok("Digievolucao para "+request.getEvolucaoEscolhida()+" salvo com sucesso!");
    }
     */
}
