package br.com.digimon.repository;

import br.com.digimon.domain.CacadaEntity;

public interface CacadaRepository {
    boolean existsByIdDigimonAndRecompensaResgatadaFalse(Long idDigimon);

    CacadaEntity findByIdDigimonAndRecompensaResgatadaFalse(Long idDigimon);

}
