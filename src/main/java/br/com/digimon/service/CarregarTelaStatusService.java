package br.com.digimon.service;

import br.com.digimon.domain.DigimonEntity;
import br.com.digimon.domain.UsuarioEntity;
import br.com.digimon.domain.dto.AtributosBaseDTO;
import br.com.digimon.domain.enums.*;
import br.com.digimon.utils.Data;
import br.com.digimon.utils.GetByJsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Service
public class CarregarTelaStatusService {

    private final DigimonService digimonService;
    private final UsuarioService usuarioService;

    public CarregarTelaStatusService(DigimonService digimonService, UsuarioService usuarioService) {
        this.digimonService = digimonService;
        this.usuarioService = usuarioService;
    }

    public Map<String, Object> carregarTelaStatus(Long idDigimon) {
        Map<String, Object> response = new LinkedHashMap<>();

        DigimonEntity digimon = digimonService.getDigimonById(idDigimon);
        UsuarioEntity usuario = usuarioService.getJogadorById(digimon.getIdJogador());

        // Chama os métodos existentes e adiciona os resultados ao response
        response.putAll(carregarInformacoes(digimon, usuario));
        response.putAll(carregarAtributos(digimon, usuario));
        //response.putAll(carregarEstatisticas(idDigimon));
        response.putAll(carregarAtributosBase(digimon));

        return response;
    }

    public Map<String, Object> carregarInformacoes(DigimonEntity digimon, UsuarioEntity usuario){
        Map<String, Object> response = new LinkedHashMap<>();

        String dataJogoDesde = Data.formatarDataCadastroParaTelaStatus(usuario.getCriadoEm());

        String indicacao =  (usuario.getIndicacao() != null) ? usuario.getIndicacao() : "Sem indicação";

        int reservaBits = digimon.getBits();
        int reservaDiamantes = digimon.getDiamantes();

        String apelidoDigimon = digimon.getNome();

        String digimonBaby1 = (digimon.getIdBaby1() != 0) ? GetByJsonUtils.filtrarDigimonsBaby1PorId(digimon.getIdBaby1()).getNome() : "-";
        String digimonBaby2 = (digimon.getIdBaby2() != 0) ? GetByJsonUtils.filtrarDigimonsBaby2PorId(digimon.getIdBaby2()).getNome() : "-";
        String digimonRookie = (digimon.getIdRookie() != 0) ? EnumDigimonRookie.getDescricaoById(digimon.getIdRookie()) : "-";
        String digimonChampion = (digimon.getIdChampion() != 0) ? EnumDigimonChampion.getDescricaoById(digimon.getIdChampion()) : "-";
        String digimonUltimate = (digimon.getIdUltimate() != 0) ? EnumDigimonUltimate.getDescricaoById(digimon.getIdUltimate()) : "-";
        String digimonMega = (digimon.getIdMega() != 0) ? EnumDigimonMega.getDescricaoById(digimon.getIdMega()) : "-";

        String tierDigimon = determinarTier(digimon);
        int nivelDigimon = digimon.getNivel();

        String bonusExperiencia = digimon.isBonusExperienciaAtivo() ? "Ativo" : "Inativo";
        String bonusBits = digimon.isBonusBitsAtivo() ? "Ativo" : "Inativo";

        preencherResponseInformacoes(response, dataJogoDesde, indicacao, reservaBits, reservaDiamantes, apelidoDigimon, digimonRookie, digimonChampion,
                digimonUltimate, digimonMega, tierDigimon, nivelDigimon, bonusExperiencia, bonusBits, digimonBaby1, digimonBaby2);

        return response;
    }


    private String determinarTier(DigimonEntity digimon) {
        if (digimon.getIdMega() != 0) {
            return "Mega";
        } else if (digimon.getIdUltimate() != 0) {
            return "Ultimate";
        } else if (digimon.getIdChampion() != 0) {
            return "Champion";
        } else {
            return "Rookie";
        }
    }

    private void preencherResponseInformacoes(Map<String, Object> response, String dataJogoDesde, String indicacao, int reservaBits, int reservaDiamantes,
                                              String apelidoDigimon, String digimonRookie, String digimonChampion, String digimonUltimate, String digimonMega,
                                              String tierDigimon, int nivelDigimon, String bonusExperiencia, String bonusBits,
                                              String digimonBaby1, String digimonBaby2) {
        response.put("dataJogoDesde", dataJogoDesde);
        response.put("indicacao", indicacao);
        response.put("reservaBits", reservaBits);
        response.put("reservaDiamantes", reservaDiamantes);
        response.put("apelidoDigimon", apelidoDigimon);
        response.put("digimonBaby1", digimonBaby1);
        response.put("digimonBaby2", digimonBaby2);
        response.put("digimonRookie", digimonRookie);
        response.put("digimonChampion", digimonChampion);
        response.put("digimonUltimate", digimonUltimate);
        response.put("digimonMega", digimonMega);
        response.put("tierDigimon", tierDigimon);
        response.put("nivelDigimon", nivelDigimon);
        response.put("bonusExperiencia", bonusExperiencia);
        response.put("bonusBits", bonusBits);
    }

    public Map<String, Object> carregarAtributos(DigimonEntity digimon, UsuarioEntity usuario){
        Map<String, Object> response = new LinkedHashMap<>();
        int forca = digimon.getAtributos().getPontosForca();
        int inteligencia = digimon.getAtributos().getPontosInteligencia();
        int conhecimento = digimon.getAtributos().getPontosConhecimento();
        int agilidade = digimon.getAtributos().getPontosAgilidade();
        int energia = digimon.getAtributos().getPontosEnergia();
        int vida = digimon.getAtributos().getPontosVida();
        int experiencia = digimon.getPontosExperiencia();
        int experienciaNecessaria = EnumNivelDigimon.getExperienciaNecessaria(digimon.getNivel());
        int modificadorForca = digimon.getAtributosModificadores().getModificadorForca();
        int modificadorInteligencia = digimon.getAtributosModificadores().getModificadorInteligencia();
        int modificadorConhecimento = digimon.getAtributosModificadores().getModificadorConhecimento();
        int modificadorAgilidade = digimon.getAtributosModificadores().getModificadorAgilidade();
        int modificadorEnergia = digimon.getAtributosModificadores().getModificadorEnergia();
        int modificadorVida = digimon.getAtributosModificadores().getModificadorVida();

        preencherResponseAtributos(response, forca, inteligencia, conhecimento, agilidade, energia, vida, experiencia, experienciaNecessaria,
                modificadorForca, modificadorInteligencia, modificadorConhecimento, modificadorAgilidade, modificadorEnergia, modificadorVida);
        return response;
    }

    private void preencherResponseAtributos(Map<String, Object> response, int forca, int inteligencia, int conhecimento, int agilidade, int energia, int vida, int experiencia, int experienciaNecessaria,
                                            int modificadorForca, int modificadorInteligencia, int modificadorConhecimento, int modificadorAgilidade, int modificadorEnergia, int modificadorVida) {
        response.put("forca", forca);
        response.put("inteligencia", inteligencia);
        response.put("conhecimento", conhecimento);
        response.put("agilidade", agilidade);
        response.put("energia", energia);
        response.put("vida", vida);
        response.put("experiencia", experiencia);
        response.put("experienciaNecessaria", experienciaNecessaria);
        response.put("modificadorForca", modificadorForca);
        response.put("modificadorInteligencia", modificadorInteligencia);
        response.put("modificadorConhecimento", modificadorConhecimento);
        response.put("modificadorAgilidade", modificadorAgilidade);
        response.put("modificadorEnergia", modificadorEnergia);
        response.put("modificadorVida", modificadorVida);
    }

    //public Map<String, Object> carregarEstatisticas(Long idDigimon){
    //    return registroConquistasService.carregarConquistas(idDigimon);
    //}

    public Map<String, Object> carregarAtributosBase(DigimonEntity digimon){
        Map<String, Object> response = new LinkedHashMap<>();
        preencherResponseAtributosBase(response, preencherAtributosBaseDto(digimon));
        return response;
    }

    private void preencherResponseAtributosBase(Map<String,Object> response, AtributosBaseDTO atributosBaseDTO){
        response.put("baseVida", atributosBaseDTO.getVida());
        response.put("baseEnergia", atributosBaseDTO.getEnergia());
        response.put("baseForca", atributosBaseDTO.getForca());
        response.put("baseInteligencia", atributosBaseDTO.getInteligencia());
        response.put("baseAgilidade", atributosBaseDTO.getAgilidade());
        response.put("baseConhecimento", atributosBaseDTO.getConhecimento());
        response.put("baseResistencia", atributosBaseDTO.getResistencia());
    }



    private AtributosBaseDTO preencherAtributosBaseDto(DigimonEntity digimon){
        AtributosBaseDTO atributosBaseDTO = new AtributosBaseDTO();
        atributosBaseDTO.setVida(digimon.getAtributosBase().getBaseVida());
        atributosBaseDTO.setEnergia(digimon.getAtributosBase().getBaseEnergia());
        atributosBaseDTO.setForca(digimon.getAtributosBase().getBaseForca());
        atributosBaseDTO.setInteligencia(digimon.getAtributosBase().getBaseInteligencia());
        atributosBaseDTO.setAgilidade(digimon.getAtributosBase().getBaseAgilidade());
        atributosBaseDTO.setConhecimento(digimon.getAtributosBase().getBaseConhecimento());
        atributosBaseDTO.setResistencia(digimon.getAtributosBase().getBaseResistencia());
        return atributosBaseDTO;
    }
}
