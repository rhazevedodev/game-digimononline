package br.com.digimon.repository.jpa;

import br.com.digimon.domain.CacadaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataCacadaRepository extends JpaRepository<CacadaEntity, Long> {

    boolean existsByIdDigimonAndRecompensaResgatadaFalse(Long idDigimon);

    CacadaEntity findByIdDigimonAndRecompensaResgatadaFalse(Long idDigimon);
}
