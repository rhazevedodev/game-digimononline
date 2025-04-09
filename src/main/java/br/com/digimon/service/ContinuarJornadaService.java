package br.com.digimon.service;

import br.com.digimon.domain.UsuarioEntity;
import br.com.digimon.domain.dto.VerificaSlotsJogadorDTO;
import br.com.digimon.utils.HeaderExtract;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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


}
