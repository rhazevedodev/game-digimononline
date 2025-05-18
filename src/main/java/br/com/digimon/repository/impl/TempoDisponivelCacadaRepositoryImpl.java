package br.com.digimon.repository.impl;

import br.com.digimon.domain.TempoDisponivelCacadaEntity;
import br.com.digimon.repository.TempoDisponivelCacadaRepository;
import br.com.digimon.repository.jpa.SpringDataTempoDisponivelCacadaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Slf4j
@Repository
public class TempoDisponivelCacadaRepositoryImpl implements TempoDisponivelCacadaRepository {

    @Autowired
    private SpringDataTempoDisponivelCacadaRepository springDataTempoDisponivelCacadaRepository;

    @Override
    public Optional<TempoDisponivelCacadaEntity> findByIdDigimonAndDataCadastro(Long idDigimon, LocalDate dataCadastro) {
        return springDataTempoDisponivelCacadaRepository.findByIdDigimonAndDataCadastro(idDigimon, dataCadastro);
    }

    @Override
    public TempoDisponivelCacadaEntity findTempoDisponivelCacadaByIdDigimonAndDataCadastro(Long idDigimon, LocalDate now) {
        return springDataTempoDisponivelCacadaRepository.findTempoDisponivelCacadaByIdDigimonAndDataCadastro(idDigimon, now);
    }

    @Override
    public void save(TempoDisponivelCacadaEntity tempoDisponivelCacadaEntity) {
        springDataTempoDisponivelCacadaRepository.save(tempoDisponivelCacadaEntity);
    }
}
