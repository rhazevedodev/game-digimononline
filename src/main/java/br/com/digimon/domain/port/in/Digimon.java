package br.com.digimon.domain.port.in;

import br.com.digimon.app.dto.SelecaoDigimonDTO;
import br.com.digimon.domain.entity.DigimonEntity;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

public interface Digimon {
    ResponseEntity<?> selecionarDigimon(SelecaoDigimonDTO selecaoDigimonDTO, HttpServletRequest request);
    String getIdByDescricao(String descricao);
    boolean verificarSeApelidoDigimonJaExiste(String apelidoDigimon);
    DigimonEntity selecionarDigimonCompleto(DigimonEntity digimonSelecionado);
}
