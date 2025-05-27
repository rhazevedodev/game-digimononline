package br.com.digimon.service;


import br.com.digimon.domain.DigimonEntity;
import br.com.digimon.domain.enums.EnumDigimonRookie;
import br.com.digimon.repository.DigimonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class DigimonService {

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

    public DigimonEntity criarDigimon(DigimonEntity digimonSelecionado) {
        log.info("Criando novo Digimon: {}", digimonSelecionado.getNome());
        return digimonRepository.criarDigimon(digimonSelecionado);
    }

    public int verificarQuantidadeDigimon(Long id) {
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

    public String getProxTierDigimon(Long idDigimon) {
        DigimonEntity digimon = digimonRepository.getDigimonById(idDigimon);

        String tier;
        if (digimon.getIdMega() != 0) {
            tier = "Jogress";
        } else if (digimon.getIdUltimate() != 0) {
            tier = "Mega";
        } else if (digimon.getIdChampion() != 0) {
            tier = "Ultimate";
        } else if (digimon.getIdRookie() != 0) {
            tier = "Champion";
        } else if (digimon.getIdBaby2() != 0) {
            tier = "Rookie";
        } else {
            tier = "Baby 2";
        }
        return tier;
    }

}
