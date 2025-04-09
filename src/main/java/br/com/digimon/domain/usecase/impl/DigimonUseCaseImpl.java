package br.com.digimon.domain.usecase.impl;

import br.com.digimon.domain.entity.DigimonEntity;
import br.com.digimon.domain.port.out.DigimonRepositoryPort;
import br.com.digimon.domain.usecase.DigimonUseCase;
import org.springframework.beans.factory.annotation.Autowired;

public class DigimonUseCaseImpl implements DigimonUseCase {

    @Autowired
    DigimonRepositoryPort digimonRepositoryPort;

    @Override
    public void validacoesCriarDigimon(DigimonEntity digimonEntity) {

    }
}
