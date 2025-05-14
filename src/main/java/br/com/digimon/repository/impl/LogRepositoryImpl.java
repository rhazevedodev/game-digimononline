package br.com.digimon.repository.impl;

import br.com.digimon.domain.LogEntity;
import br.com.digimon.repository.LogRepository;
import br.com.digimon.repository.jpa.SpringDataLogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class LogRepositoryImpl implements LogRepository {

    @Autowired
    private SpringDataLogRepository springDataLogRepository;

    @Override
    public void save(LogEntity entity) {
        springDataLogRepository.save(entity);
    }
}
