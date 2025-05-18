package br.com.digimon.repository.impl;

import br.com.digimon.domain.CacadaEntity;
import br.com.digimon.repository.CacadaRepository;
import br.com.digimon.repository.jpa.SpringDataCacadaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class CacadaRepositoryImpl implements CacadaRepository {

    @Autowired
    private SpringDataCacadaRepository springDataCacadaRepository;

    @Override
    public boolean existsByIdDigimonAndRecompensaResgatadaFalse(Long idDigimon) {
        return springDataCacadaRepository.existsByIdDigimonAndRecompensaResgatadaFalse(idDigimon);
    }

    @Override
    public CacadaEntity findByIdDigimonAndRecompensaResgatadaFalse(Long idDigimon) {
        return springDataCacadaRepository.findByIdDigimonAndRecompensaResgatadaFalse(idDigimon);
    }
}
