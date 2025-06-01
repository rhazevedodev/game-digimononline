package br.com.digimon.service;

import br.com.digimon.domain.InventarioEntity;
import br.com.digimon.domain.dto.GuardarNoInventarioRecompensaCacadaDTO;
import br.com.digimon.domain.fromJson.cacada.ItemRecompensaCacada;
import br.com.digimon.domain.fromJson.itens.outros.Outros;
import br.com.digimon.repository.InventarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class InventarioService {

    private InventarioRepository inventarioRepository;

    public InventarioService(InventarioRepository inventarioRepository) {
        this.inventarioRepository = inventarioRepository;
    }

    public List<InventarioEntity> getInventarioDoJogador(Long idDigimon, int idCategoria) {
        return inventarioRepository.findByIdDigimonAndIdCategoria(idDigimon,idCategoria);
    }

    @Transactional
    public void adicionarItemAoInventario(Long idDigimon, List<GuardarNoInventarioRecompensaCacadaDTO> listaItens) {
        List<InventarioEntity> inventarioAtual = inventarioRepository.findByIdDigimon(idDigimon);

        for (GuardarNoInventarioRecompensaCacadaDTO itemDTO : listaItens) {
            // Verifica se o item já existe no inventário pelo id do item
            Optional<InventarioEntity> itemExistenteOpt = inventarioAtual.stream()
                    .filter(i -> i.getIdItem() ==(itemDTO.getId()))
                    .findFirst();

            if (itemExistenteOpt.isPresent()) {
                // Atualiza a quantidade do item existente
                InventarioEntity itemExistente = itemExistenteOpt.get();
                itemExistente.setQuantidade(itemExistente.getQuantidade() + itemDTO.getQuantidade());
                itemExistente.setDataUltimaAlteracao(LocalDateTime.now());
                inventarioRepository.save(itemExistente);
            } else {
                // Cria novo item no inventário
                InventarioEntity novoItem = new InventarioEntity();
                novoItem.setIdDigimon(idDigimon);
                novoItem.setIdItem(itemDTO.getId());
                novoItem.setNomeItem(itemDTO.getNome());
                novoItem.setDescricaoItem(itemDTO.getDescricao());
                novoItem.setQuantidade(itemDTO.getQuantidade());
                novoItem.setIdCategoria(itemDTO.getIdCategoria());
                novoItem.setValorCompra(itemDTO.getValorCompra());
                novoItem.setValorVenda(itemDTO.getValorVenda());
                novoItem.setDataUltimaAlteracao(LocalDateTime.now());
                inventarioRepository.save(novoItem);
            }
        }
    }


}
