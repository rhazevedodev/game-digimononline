package br.com.digimon.repository;

import br.com.digimon.domain.PremiumEntity;

import java.util.Optional;

public interface PremiumRepository {
    Optional<PremiumEntity> findLatestByIdDigimon(Long idDigimon);

    void save(PremiumEntity premium);
}
