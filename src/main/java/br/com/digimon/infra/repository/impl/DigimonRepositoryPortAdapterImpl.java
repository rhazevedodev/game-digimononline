package br.com.digimon.infra.repository.impl;

import br.com.digimon.domain.entity.DigimonEntity;
import br.com.digimon.domain.port.out.DigimonRepositoryPort;
import br.com.digimon.infra.repository.jpa.SpringDataDigimonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class DigimonRepositoryPortAdapterImpl implements DigimonRepositoryPort {

    @Autowired
    private SpringDataDigimonRepository springDataDigimonRepository;

    @Override
    public boolean verificarSeApelidoDigimonJaExiste(String apelidoDigimon) {
        return springDataDigimonRepository.existsByNome(apelidoDigimon);
    }

    @Override
    public DigimonEntity criarDigimon(DigimonEntity digimonSelecionado) {
        return springDataDigimonRepository.save(digimonSelecionado);
    }
}
