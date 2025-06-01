package br.com.digimon.repository.impl;

import br.com.digimon.domain.CacadaEntity;
import br.com.digimon.repository.CacadaRepository;
import br.com.digimon.repository.jpa.SpringDataCacadaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class CacadaRepositoryImpl implements CacadaRepository {

    @Autowired
    private SpringDataCacadaRepository springDataCacadaRepository;

    @Override
    public void save(CacadaEntity cacadaEntity) {
        log.info("Salvando caçada: {}", cacadaEntity);
        try {
            springDataCacadaRepository.save(cacadaEntity);
            log.info("Caçada salva com sucesso: {}", cacadaEntity);
        } catch (Exception e) {
            log.error("Erro ao salvar caçada: {}", e.getMessage());
            throw new RuntimeException("Erro ao salvar caçada", e);
        }
    }

    @Override
    public List<CacadaEntity> findByIdDigimonAndRecompensaResgatadaFalse(Long idDigimon) {
        log.info("Buscando caçadas não resgatadas para o Digimon com ID: {}", idDigimon);
        try {
            List<CacadaEntity> cacadas = springDataCacadaRepository.findByIdDigimonAndRecompensaResgatadaFalse(idDigimon);
            log.info("Caçadas encontradas: {}", cacadas.size());
            return cacadas;
        } catch (Exception e) {
            log.error("Erro ao buscar caçadas: {}", e.getMessage());
            throw new RuntimeException("Erro ao buscar caçadas", e);
        }
    }

    @Override
    public CacadaEntity findByIdDigimonAndIdCacadaAndRecompensaResgatadaFalse(Long idDigimon, int idCacada) {
        return springDataCacadaRepository.findByIdDigimonAndIdCacadaAndRecompensaResgatadaFalse(idDigimon, idCacada);
    }
}
