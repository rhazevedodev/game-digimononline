package br.com.digimon.infra.repository.jpa;

import br.com.digimon.domain.entity.DigimonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataDigimonRepository extends JpaRepository<DigimonEntity, Long> {

    boolean existsByNome(String apelidoDigimon);

}
