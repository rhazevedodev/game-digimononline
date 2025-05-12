package br.com.digimon.repository.impl;

import br.com.digimon.domain.PremiumEntity;
import br.com.digimon.repository.PremiumRepository;
import br.com.digimon.repository.jpa.SpringDataPremiumRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Slf4j
@Repository
public class PremiumRepositoryImpl implements PremiumRepository {

    private SpringDataPremiumRepository springDataPremiumRepository;

    public PremiumRepositoryImpl(SpringDataPremiumRepository springDataPremiumRepository) {
        this.springDataPremiumRepository = springDataPremiumRepository;
    }

    @Override
    public Optional<PremiumEntity> findLatestByIdDigimon(Long idDigimon) {
        return springDataPremiumRepository.findLatestByIdDigimon(idDigimon);
    }

    @Override
    public void save(PremiumEntity premium) {
        springDataPremiumRepository.save(premium);
    }
}
