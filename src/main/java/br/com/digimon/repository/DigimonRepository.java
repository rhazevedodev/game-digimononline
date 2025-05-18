package br.com.digimon.repository;

import br.com.digimon.domain.DigimonEntity;

import java.util.List;

public interface DigimonRepository {

    boolean verificarSeApelidoDigimonJaExiste(String apelidoDigimon);
    DigimonEntity criarDigimon(DigimonEntity digimonSelecionado);

    int countBySacrificadoFalseAndIdJogador(Long id);

    List<DigimonEntity> findByIdJogadorAndSacrificadoFalse(Long id);

    DigimonEntity getDigimonById(Long id);

    Boolean existsById(Long idDigimon);
}
