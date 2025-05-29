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
import br.com.digimon.domain.fromJson.*;
import br.com.digimon.utils.HeaderExtract;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ContinuarJornadaService {

    private DigimonService digimonService;

    private UsuarioService usuarioService;

    private TokenService tokenService;

    private JsonService jsonService;

    public ContinuarJornadaService(DigimonService digimonService, UsuarioService usuarioService, TokenService tokenService, JsonService jsonService) {
        this.digimonService = digimonService;
        this.usuarioService = usuarioService;
        this.tokenService = tokenService;
        this.jsonService = jsonService;
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
            ListaDigimonMegasJson listaDigimonMegasJson = jsonService.carregarDigiMegas();
            for (DigimonMegaJson mega : listaDigimonMegasJson.getDigimonsMegas()) {
                if (mega.getId() == digimon.getIdMega()) {
                    carregarImagemContinuarJornadaDTO.setUrlImagemDigimon(mega.getImage());
                    carregarImagemContinuarJornadaDTO.setNomeDigimon(mega.getNome());
                    carregarImagemContinuarJornadaDTO.setTierDigimon(digimon.getTierMega());
                    break;
                }
            }
        } else if (digimon.getIdUltimate() != 0) {
            ListaDigimonUltimatesJson listaDigimonUltimatesJson = jsonService.carregarDigiUltimates();
            for (DigimonUltimateJson ultimate : listaDigimonUltimatesJson.getDigimonsUltimates()) {
                if (ultimate.getId() == digimon.getIdUltimate()) {
                    carregarImagemContinuarJornadaDTO.setUrlImagemDigimon(ultimate.getImage());
                    carregarImagemContinuarJornadaDTO.setNomeDigimon(ultimate.getNome());
                    carregarImagemContinuarJornadaDTO.setTierDigimon(digimon.getTierUltimate());
                    break;
                }
            }
        } else if (digimon.getIdChampion() != 0) {
            ListaDigimonChampionsJson listaDigimonChampionsJson = jsonService.carregarDigiChampions();
            for (DigimonChampionJson champion : listaDigimonChampionsJson.getDigimonsChampions()) {
                if (champion.getId() == digimon.getIdChampion()) {
                    carregarImagemContinuarJornadaDTO.setUrlImagemDigimon(champion.getImage());
                    carregarImagemContinuarJornadaDTO.setNomeDigimon(champion.getNome());
                    carregarImagemContinuarJornadaDTO.setTierDigimon(digimon.getTierChampion());
                    break;
                }
            }
        } else if (digimon.getIdRookie() != 0) {
            ListaDigimonRookiesJson listaDigimonRookiesJson = jsonService.carregarDigiRookies();
            for (DigimonRookieJson rookie : listaDigimonRookiesJson.getDigimonsRookies()) {
                if (rookie.getId() == digimon.getIdRookie()) {
                    carregarImagemContinuarJornadaDTO.setUrlImagemDigimon(rookie.getImage());
                    carregarImagemContinuarJornadaDTO.setNomeDigimon(rookie.getNome());
                    carregarImagemContinuarJornadaDTO.setTierDigimon(digimon.getTierRookie());
                    break;
                }
            }
        } else if (digimon.getIdBaby2() != 0) {
            ListaDigimonBabys2Json listaDigimonBabys2Json = jsonService.carregarDigiBabys2();
            for (DigimonBaby2Json baby2 : listaDigimonBabys2Json.getDigimonsBaby2()) {
                if (baby2.getId() == digimon.getIdBaby2()) {
                    carregarImagemContinuarJornadaDTO.setUrlImagemDigimon(baby2.getImage());
                    carregarImagemContinuarJornadaDTO.setNomeDigimon(baby2.getNome());
                    carregarImagemContinuarJornadaDTO.setTierDigimon(0);
                    break;
                }
            }
        } else if (digimon.getIdBaby1() != 0) {
            ListaDigimonBabys1Json listaDigimonBabys1Json = jsonService.carregarDigiBabys1();
            for (DigimonBaby1Json baby1 : listaDigimonBabys1Json.getDigimonsBaby1()) {
                if (baby1.getId() == digimon.getIdBaby1()) {
                    carregarImagemContinuarJornadaDTO.setUrlImagemDigimon(baby1.getImage());
                    carregarImagemContinuarJornadaDTO.setNomeDigimon(baby1.getNome());
                    carregarImagemContinuarJornadaDTO.setTierDigimon(0);
                    break;
                }
            }
        }
        return carregarImagemContinuarJornadaDTO;
    }
}
