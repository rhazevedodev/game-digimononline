package br.com.digimon.service;

import br.com.digimon.domain.DigimonEntity;
import br.com.digimon.domain.UsuarioEntity;
import br.com.digimon.domain.dto.SelecaoDigitamaDTO;
import br.com.digimon.utils.HeaderExtract;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SelecionarDigitamaService {

    private UsuarioService usuarioService;

    private TokenService tokenService;

    public SelecionarDigitamaService(UsuarioService usuarioService, TokenService tokenService) {
        this.usuarioService = usuarioService;
        this.tokenService = tokenService;
    }

    public DigimonEntity selecionarDigitama(SelecaoDigitamaDTO selecaoDigitamaDTO, HttpServletRequest httpServletRequest) {
        log.info("Selecionando Digitama: ");
        String nomeUsuario = "";
        try {
            String jwt = HeaderExtract.extrairTokenDoHeader(httpServletRequest);
            nomeUsuario = tokenService.obterUsuarioPorToken(jwt);
            UsuarioEntity usuarioEntity = usuarioService.obterUsuarioPorNome(nomeUsuario);

            DigimonEntity digimonSelecionado = new DigimonEntity();
            digimonSelecionado.setIdJogador(usuarioEntity.getId());
            digimonSelecionado.setIdDigitama(selecaoDigitamaDTO.getIdDigitama());

            return digimonSelecionado;
        } catch (Exception e) {
            log.error("Erro ao selecionar Digitama: {}", e.getMessage());
            return new DigimonEntity();
        }
    }
}
