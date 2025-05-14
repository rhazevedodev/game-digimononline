package br.com.digimon.repository.jpa;

import br.com.digimon.domain.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataLogRepository extends JpaRepository<LogEntity, Long> {
    // No additional methods are needed here for now
}
