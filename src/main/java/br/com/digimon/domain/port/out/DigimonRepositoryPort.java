package br.com.digimon.domain.port.out;

import br.com.digimon.domain.entity.DigimonEntity;

public interface DigimonRepositoryPort {
    boolean verificarSeApelidoDigimonJaExiste(String apelidoDigimon);

    DigimonEntity criarDigimon(DigimonEntity digimonSelecionado);
}
