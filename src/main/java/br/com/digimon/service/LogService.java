package br.com.digimon.service;

import br.com.digimon.domain.LogEntity;
import br.com.digimon.repository.LogRepository;
import org.springframework.stereotype.Service;

@Service
public class LogService {

    private final LogRepository logRepository;

    public LogService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public void saveLog(LogEntity logEntity) {
        logRepository.save(logEntity);
    }
}
