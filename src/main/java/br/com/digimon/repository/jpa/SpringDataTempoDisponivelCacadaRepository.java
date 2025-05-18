package br.com.digimon.repository.jpa;

import br.com.digimon.domain.TempoDisponivelCacadaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface SpringDataTempoDisponivelCacadaRepository extends JpaRepository<TempoDisponivelCacadaEntity, Long> {
    Optional<TempoDisponivelCacadaEntity> findByIdDigimonAndDataCadastro(Long idDigimon, LocalDate dataCadastro);

    TempoDisponivelCacadaEntity findTempoDisponivelCacadaByIdDigimonAndDataCadastro(Long idDigimon, LocalDate now);
}
