package br.com.digimon.repository.jpa;

import br.com.digimon.domain.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface SpringDataUsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    Optional<UsuarioEntity> findByNomeUsuario(String nomeUsuario);
    Optional<Object> findByEmail(String email);

}
