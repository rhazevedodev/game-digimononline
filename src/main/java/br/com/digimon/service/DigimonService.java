package br.com.digimon.service;


import br.com.digimon.domain.*;
import br.com.digimon.domain.dto.SelecaoDigimonDTO;
import br.com.digimon.domain.enums.EnumDigimonRookie;
import br.com.digimon.domain.enums.EnumElementos;
import br.com.digimon.exception.ApelidoDigimonJaEscolhidoException;
import br.com.digimon.repository.DigimonRepository;
import br.com.digimon.utils.AtributosBaseDigimons;
import br.com.digimon.utils.HeaderExtract;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DigimonService{

    @Autowired
    private DigimonRepository digimonRepository;

    private TokenService tokenService;

    public DigimonService(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    public String getIdByDescricao(String descricao) {
        return EnumDigimonRookie.getIdByDescricao(descricao);
    }

    public boolean verificarSeApelidoDigimonJaExiste(String apelidoDigimon) {
        return digimonRepository.verificarSeApelidoDigimonJaExiste(apelidoDigimon);
    }

      public DigimonEntity criarDigimon (DigimonEntity digimonSelecionado) {
            log.info("Criando novo Digimon: {}", digimonSelecionado.getNome());
            return digimonRepository.criarDigimon(digimonSelecionado);
      }

    public int verificarQuantidadeDigimon(Long id){
        log.info("Verificando quantidade de Digimons para o jogador: {}", id);
        int quantidadeDigimons = digimonRepository.countBySacrificadoFalseAndIdJogador(id);
        log.info("Quantidade de Digimons encontrados: {}", quantidadeDigimons);
        return quantidadeDigimons;
    }
}
