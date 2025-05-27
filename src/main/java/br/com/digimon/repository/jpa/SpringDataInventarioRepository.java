package br.com.digimon.repository.jpa;

import br.com.digimon.domain.InventarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataInventarioRepository extends JpaRepository<InventarioEntity, Long> {
    List<InventarioEntity> findByIdDigimonAndIdCategoria(Long idDigimon, int idCategoria);
}
