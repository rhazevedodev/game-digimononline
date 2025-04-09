package br.com.digimon.repository.jpa;

import br.com.digimon.domain.DigimonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataDigimonRepository extends JpaRepository<DigimonEntity, Long> {

    boolean existsByNome(String apelidoDigimon);

    int countBySacrificadoFalseAndIdJogador(Long id);

    List<DigimonEntity> findByIdJogadorAndSacrificadoFalse(Long id);

    DigimonEntity getDigimonById(Long id);
}
