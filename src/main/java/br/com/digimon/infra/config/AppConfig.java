package br.com.digimon.infra.config;

import br.com.digimon.domain.port.out.TokenRepositoryPort;
import br.com.digimon.infra.repository.impl.TokenRepositoryAdapterImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public TokenRepositoryPort tokenRepositoryPort() {
        return new TokenRepositoryAdapterImpl();
    }
}
