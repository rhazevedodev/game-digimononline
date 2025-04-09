package br.com.digimon.repository.jpa;

import br.com.digimon.domain.DigimonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataDigimonRepository extends JpaRepository<DigimonEntity, Long> {

    boolean existsByNome(String apelidoDigimon);

}
