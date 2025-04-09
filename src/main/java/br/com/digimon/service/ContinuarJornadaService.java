package br.com.digimon.service;

import br.com.digimon.domain.DigimonEntity;
import br.com.digimon.domain.UsuarioEntity;
import br.com.digimon.domain.dto.CarregarImagemContinuarJornadaDTO;
import br.com.digimon.domain.dto.ContinuarJornadaDTO;
import br.com.digimon.domain.dto.VerificaSlotsJogadorDTO;
import br.com.digimon.domain.enums.EnumDigimonChampion;
import br.com.digimon.domain.enums.EnumDigimonMega;
import br.com.digimon.domain.enums.EnumDigimonRookie;
import br.com.digimon.domain.enums.EnumDigimonUltimate;
import br.com.digimon.utils.HeaderExtract;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ContinuarJornadaService {

    private DigimonService digimonService;

    private UsuarioService usuarioService;

    private TokenService tokenService;


    public ContinuarJornadaService(DigimonService digimonService, UsuarioService usuarioService, TokenService tokenService) {
        this.digimonService = digimonService;
        this.usuarioService = usuarioService;
        this.tokenService = tokenService;
    }

    public VerificaSlotsJogadorDTO validarSlotsDigimon(HttpServletRequest request) {
        try {
            String jwt = HeaderExtract.extrairTokenDoHeader(request);
            String nomeUsuario = tokenService.obterUsuarioPorToken(jwt);
            UsuarioEntity usuarioEntity = usuarioService.obterUsuarioPorNome(nomeUsuario);
            int quantiaDigimons = digimonService.verificarQuantidadeDigimon(usuarioEntity.getId());
            return new VerificaSlotsJogadorDTO(quantiaDigimons, usuarioEntity.getSlotsDigimon());
        } catch (Exception e) {
            log.error("Erro ao validar slots de digimon: {}", e.getMessage());
            throw new RuntimeException("Erro ao validar slots de digimon: " + e.getMessage());
        }
    }

    public List<ContinuarJornadaDTO> carregarDigimonsContinuarJornada(HttpServletRequest request){
        log.info("Carregando Digimons para continuar jornada");
        String jwt = HeaderExtract.extrairTokenDoHeader(request);
        String nomeUsuario = tokenService.obterUsuarioPorToken(jwt);
        UsuarioEntity usuarioEntity = usuarioService.obterUsuarioPorNome(nomeUsuario);

        List<DigimonEntity> digimons = digimonService.findByIdJogadorAndSacrificadoFalse(usuarioEntity.getId());
        List<ContinuarJornadaDTO> continuarJornadaDTOList = new ArrayList<>();
        for(DigimonEntity digimon : digimons){
            CarregarImagemContinuarJornadaDTO  carregarImagemContinuarJornadaDTO = carregarImagemDigimon(digimon.getId());
            String urlImagemDigimon = carregarImagemContinuarJornadaDTO.getUrlImagemDigimon();
            continuarJornadaDTOList.add(new ContinuarJornadaDTO(digimon,urlImagemDigimon));
        }
        return continuarJornadaDTOList;
    }

    public CarregarImagemContinuarJornadaDTO carregarImagemDigimon(Long idDigimon){
        CarregarImagemContinuarJornadaDTO carregarImagemContinuarJornadaDTO = new CarregarImagemContinuarJornadaDTO();
        DigimonEntity digimon = digimonService.getDigimonById(idDigimon);

        if(digimon.getIdMega() != 0) {
            EnumDigimonMega mega = EnumDigimonMega.getEnumById(digimon.getIdMega());
            carregarImagemContinuarJornadaDTO.setUrlImagemDigimon(mega.getUrlImg());
            carregarImagemContinuarJornadaDTO.setNomeDigimon(mega.getDescricao());
            carregarImagemContinuarJornadaDTO.setTierDigimon(digimon.getTierMega());
        } else if (digimon.getIdUltimate() != 0) {
            EnumDigimonUltimate ultimate = EnumDigimonUltimate.getEnumById(digimon.getIdUltimate());
            carregarImagemContinuarJornadaDTO.setUrlImagemDigimon(ultimate.getUrlImg());
            carregarImagemContinuarJornadaDTO.setNomeDigimon(ultimate.getDescricao());
            carregarImagemContinuarJornadaDTO.setTierDigimon(digimon.getTierUltimate());
        } else if (digimon.getIdChampion() != 0) {
            EnumDigimonChampion champion = EnumDigimonChampion.getEnumById(digimon.getIdChampion());
            carregarImagemContinuarJornadaDTO.setUrlImagemDigimon(champion.getUrlImg());
            carregarImagemContinuarJornadaDTO.setNomeDigimon(champion.getDescricao());
            carregarImagemContinuarJornadaDTO.setTierDigimon(digimon.getTierChampion());
        } else {
            EnumDigimonRookie rookie = EnumDigimonRookie.getEnumById(digimon.getIdRookie());
            carregarImagemContinuarJornadaDTO.setUrlImagemDigimon(rookie.getUrlImg());
            carregarImagemContinuarJornadaDTO.setNomeDigimon(rookie.getDescricao());
            carregarImagemContinuarJornadaDTO.setTierDigimon(digimon.getTierRookie());
        }
        return carregarImagemContinuarJornadaDTO;
    }


}
