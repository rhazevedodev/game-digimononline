package br.com.digimon.domain.port.out;

import br.com.digimon.domain.entity.TokenEntity;
import br.com.digimon.domain.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepositoryPort {

    void criarToken(TokenEntity tokenEntity);

    Optional<TokenEntity> findByToken(String token);

    Optional<TokenEntity> findByUsername(String usuario);
}
