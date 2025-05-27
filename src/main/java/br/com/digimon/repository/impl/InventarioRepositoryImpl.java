package br.com.digimon.repository.impl;

import br.com.digimon.domain.InventarioEntity;
import br.com.digimon.repository.InventarioRepository;
import br.com.digimon.repository.jpa.SpringDataInventarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class InventarioRepositoryImpl implements InventarioRepository {

    @Autowired
    private SpringDataInventarioRepository springDataInventarioRepository;

    @Override
    public List<InventarioEntity> findByIdDigimonAndIdCategoria(Long idDigimon, int idCategoria) {
        return springDataInventarioRepository.findByIdDigimonAndIdCategoria(idDigimon, idCategoria);
    }
}
