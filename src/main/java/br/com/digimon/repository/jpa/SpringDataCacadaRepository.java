package br.com.digimon.repository.jpa;

import br.com.digimon.domain.CacadaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataCacadaRepository extends JpaRepository<CacadaEntity, Long> {

    List<CacadaEntity> findByIdDigimonAndRecompensaResgatadaFalse(Long idDigimon);
}
