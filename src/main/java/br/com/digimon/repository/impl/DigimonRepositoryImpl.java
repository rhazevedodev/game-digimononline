package br.com.digimon.repository.impl;

import br.com.digimon.domain.DigimonEntity;
import br.com.digimon.repository.DigimonRepository;
import br.com.digimon.repository.jpa.SpringDataDigimonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class DigimonRepositoryImpl implements DigimonRepository {

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
