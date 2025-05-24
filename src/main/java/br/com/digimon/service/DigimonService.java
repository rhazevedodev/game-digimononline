package br.com.digimon.service;


import br.com.digimon.domain.*;
import br.com.digimon.domain.dto.AtributosBaseDTO;
import br.com.digimon.domain.dto.ContinuarJornadaDTO;
import br.com.digimon.domain.dto.SelecaoDigimonDTO;
import br.com.digimon.domain.enums.*;
import br.com.digimon.domain.fromJson.DigitamasJson;
import br.com.digimon.exception.ApelidoDigimonJaEscolhidoException;
import br.com.digimon.repository.DigimonRepository;
import br.com.digimon.utils.AtributosBaseDigimons;
import br.com.digimon.utils.Data;
import br.com.digimon.utils.GetByJsonUtils;
import br.com.digimon.utils.HeaderExtract;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class DigimonService{

    private DigimonRepository digimonRepository;

    private TokenService tokenService;

    public DigimonService(DigimonRepository digimonRepository, TokenService tokenService) {
        this.digimonRepository = digimonRepository;
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


    public List<DigimonEntity> findByIdJogadorAndSacrificadoFalse(Long id) {
        log.info("Buscando Digimons para o jogador: {}", id);
        List<DigimonEntity> digimons = digimonRepository.findByIdJogadorAndSacrificadoFalse(id);
        log.info("Quantidade de Digimons encontrados: {}", digimons.size());
        return digimons;
    }

    public DigimonEntity getDigimonById(Long idDigimon) {
        log.info("Buscando Digimon pelo ID: {}", idDigimon);
        DigimonEntity digimon = digimonRepository.getDigimonById(idDigimon);
        if (digimon != null) {
            log.info("Digimon encontrado: {}", digimon.getNome());
            return digimon;
        } else {
            log.warn("Nenhum Digimon encontrado com o ID: {}", idDigimon);
            return null;
        }
    }

    public Map<String, Object> carregarVidaEnergia(Long idDigimonUsuario) {
        Map<String, Object> response = new LinkedHashMap<>();

        DigimonEntity digimon = getDigimonById(idDigimonUsuario);
        response.put("nivel", digimon.getNivel());
        response.put("vida", digimon.getAtributos().getPontosVida());
        response.put("baseVida", digimon.getAtributosBase().getBaseVida());
        response.put("manipulavelVida", digimon.getAtributosManipulaveis().getManipulaveisVida());
        response.put("modificadoresVida", digimon.getAtributosModificadores().getModificadorVida());
        response.put("energia", digimon.getAtributos().getPontosEnergia());
        response.put("baseEnergia", digimon.getAtributosBase().getBaseEnergia());
        response.put("manipulavelEnergia", digimon.getAtributosManipulaveis().getManipulaveisEnergia());
        response.put("modificadoresEnergia", digimon.getAtributosModificadores().getModificadorEnergia());

        return response;
    }


    public Boolean verificarExistenciaDigimon(Long idDigimon) {
        return digimonRepository.existsById(idDigimon);
    }

    @Cacheable(value = "digitamasJson")
    public DigitamasJson getDigitamasByJson() {
        return GetByJsonUtils.carregarDigitamas();
    }


}
