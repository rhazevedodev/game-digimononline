package br.com.digimon.repository;

import br.com.digimon.domain.InventarioEntity;

import java.util.List;

public interface InventarioRepository {
    List<InventarioEntity> findByIdDigimonAndIdCategoria(Long idDigimon, int idCategoria);

    List<InventarioEntity> findByIdDigimon(Long idDigimon);

    void save(InventarioEntity novoItem);
}
