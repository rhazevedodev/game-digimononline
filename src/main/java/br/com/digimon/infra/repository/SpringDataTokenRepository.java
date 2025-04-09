package br.com.digimon.infra.repository;

import br.com.digimon.domain.entity.TokenEntity;
import br.com.digimon.domain.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataTokenRepository extends JpaRepository<TokenEntity, Long> {
    Optional<TokenEntity> findByToken(String token);

    Optional<TokenEntity> findByUsername(String usuario);
}
