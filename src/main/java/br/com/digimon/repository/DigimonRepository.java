package br.com.digimon.repository;

import br.com.digimon.domain.DigimonEntity;

public interface DigimonRepository {

    boolean verificarSeApelidoDigimonJaExiste(String apelidoDigimon);
    DigimonEntity criarDigimon(DigimonEntity digimonSelecionado);
}
