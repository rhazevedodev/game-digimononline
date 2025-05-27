package br.com.digimon.service;

import br.com.digimon.domain.InventarioEntity;
import br.com.digimon.repository.InventarioRepository;
import org.springframework.stereotype.Service;

import java.io.Serial;
import java.util.List;

@Service
public class InventarioService {

    private InventarioRepository inventarioRepository;

    public InventarioService(InventarioRepository inventarioRepository) {
        this.inventarioRepository = inventarioRepository;
    }

    public List<InventarioEntity> getInventarioDoJogador(Long idDigimon, int idCategoria) {
        return inventarioRepository.findByIdDigimonAndIdCategoria(idDigimon,idCategoria);
    }

}
