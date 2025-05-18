package br.com.digimon.repository;

import br.com.digimon.domain.TempoDisponivelCacadaEntity;

import java.time.LocalDate;
import java.util.Optional;

public interface TempoDisponivelCacadaRepository {
    Optional<TempoDisponivelCacadaEntity> findByIdDigimonAndDataCadastro(Long idDigimon, LocalDate dataCadastro);

    TempoDisponivelCacadaEntity findTempoDisponivelCacadaByIdDigimonAndDataCadastro(Long idDigimon, LocalDate now);

    void save(TempoDisponivelCacadaEntity tempoDisponivelCacadaEntity);
}
