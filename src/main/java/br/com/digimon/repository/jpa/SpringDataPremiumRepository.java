package br.com.digimon.repository.jpa;

import br.com.digimon.domain.PremiumEntity;
import br.com.digimon.repository.PremiumRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SpringDataPremiumRepository extends JpaRepository<PremiumEntity, Long> {

    @Query("SELECT p FROM premium p WHERE p.idDigimon = :idDigimon ORDER BY p.dataFim DESC limit 1")
    Optional<PremiumEntity> findLatestByIdDigimon(@Param("idDigimon") Long idDigimon);
}
