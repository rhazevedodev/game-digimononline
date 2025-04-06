package br.com.digimon.infra.repository;

import br.com.digimon.domain.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface SpringDataUsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    // Aqui você pode adicionar métodos personalizados, se necessário
    // Exemplo: List<UsuarioEntity> findByNome(String nome);
}
