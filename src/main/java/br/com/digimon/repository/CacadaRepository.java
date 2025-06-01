package br.com.digimon.repository;

import br.com.digimon.domain.CacadaEntity;

import java.util.List;

public interface CacadaRepository {

    void save(CacadaEntity cacadaEntity);

    List<CacadaEntity> findByIdDigimonAndRecompensaResgatadaFalse(Long idDigimon);

    CacadaEntity findByIdDigimonAndIdCacadaAndRecompensaResgatadaFalse(Long idDigimon, int idCacada);
}
