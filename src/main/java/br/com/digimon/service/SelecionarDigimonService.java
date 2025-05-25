package br.com.digimon.service;

import br.com.digimon.domain.*;
import br.com.digimon.domain.dto.ResponsePadraoDTO;
import br.com.digimon.domain.dto.RequestSelecaoDigimonDTO;
import br.com.digimon.domain.enums.EnumDigimonRookie;
import br.com.digimon.domain.enums.EnumElementos;
import br.com.digimon.exception.ApelidoDigimonJaEscolhidoException;
import br.com.digimon.utils.AtributosBaseDigimons;
import br.com.digimon.utils.HeaderExtract;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SelecionarDigimonService {

    private DigimonService digimonService;

    private UsuarioService usuarioService;

    private TokenService tokenService;

    private LogService logService;

    public SelecionarDigimonService(DigimonService digimonService, UsuarioService usuarioService, TokenService tokenService, LogService logService) {
        this.digimonService = digimonService;
        this.usuarioService = usuarioService;
        this.tokenService = tokenService;
        this.logService = logService;
    }

    public ResponseEntity<?> selecionarDigimon(RequestSelecaoDigimonDTO requestSelecaoDigimonDTO, HttpServletRequest request) {
        log.info("Selecionando Digimon: {}", requestSelecaoDigimonDTO.getNomeDigimon());
        String nomeUsuario = "";
        try {
            String jwt = HeaderExtract.extrairTokenDoHeader(request);
            nomeUsuario = tokenService.obterUsuarioPorToken(jwt);
            UsuarioEntity usuarioEntity = usuarioService.obterUsuarioPorNome(nomeUsuario);

            DigimonEntity digimonSelecionado = new DigimonEntity();
            digimonSelecionado.setIdJogador(usuarioEntity.getId());
            digimonSelecionado.setIdRookie(Integer.parseInt(digimonService.getIdByDescricao(requestSelecaoDigimonDTO.getNomeDigimon())));

            boolean apelidoJaEscolhido = digimonService.verificarSeApelidoDigimonJaExiste(requestSelecaoDigimonDTO.getApelidoDigimon());

            if (apelidoJaEscolhido) {
                throw new ApelidoDigimonJaEscolhidoException("Esse apelido de digimon já foi escolhido, escolha outro!");
            } else {
                digimonSelecionado.setNome(requestSelecaoDigimonDTO.getApelidoDigimon());
            }

            DigimonEntity novoDigimon = selecionarDigimonCompleto(digimonSelecionado);

            log.info("Digimon selecionado com sucesso para o usuário: {}", nomeUsuario);

            ResponsePadraoDTO primeiroAcesso = usuarioService.verificarPrimeiroAcesso(nomeUsuario);
            if(primeiroAcesso.getMensagem().equals("Primeiro acesso confirmado")){
                usuarioEntity.setPrimeiroAcesso(false);
                usuarioService.atualizarUsuario(usuarioEntity);
            }

            return ResponseEntity.ok(novoDigimon);
        } catch (ApelidoDigimonJaEscolhidoException e) {
            log.warn("Apelido já escolhido: {}", e.getMessage());
            LogEntity logEntity = new LogEntity();
            logEntity.setAcao("FALHA SELEÇÃO DIGIMON");
            logEntity.setDetalhes("Falha ao selecionar Digimon para o usuario: " + nomeUsuario + ", Erro: " + e.getMessage());

            logService.saveLog(logEntity);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            log.error("Erro ao selecionar o Digimon: {}", e.getMessage());
            LogEntity logEntity = new LogEntity();
            logEntity.setAcao("FALHA SELEÇÃO DIGIMON");
            logEntity.setDetalhes("Falha ao selecionar Digimon para o usuario: " + nomeUsuario + ", Erro: " + e.getMessage());

            logService.saveLog(logEntity);
            return ResponseEntity.status(500).body("Erro ao selecionar o Digimon");
        }
    }

    public DigimonEntity selecionarDigimonCompleto(DigimonEntity digimonSelecionado) {
        usuarioService.validarJogadorExiste(digimonSelecionado.getIdJogador());
        if (digimonSelecionado.getIdRookie() <= 0) {
            throw new RuntimeException("o parametro idRookie precisa ser maior que 0");
        }

        EnumDigimonRookie rookie = EnumDigimonRookie.getEnumById(digimonSelecionado.getIdRookie());

        String elementoRookie = EnumDigimonRookie.getElementoByEnum(rookie);

        int idElementoPrimitivoRookie = EnumElementos.getIdElemento(elementoRookie);

        AtributosBaseEntity atributosBase = AtributosBaseDigimons.definirAtributosBaseDigimonsRookies(new AtributosBaseEntity(), rookie);
        digimonSelecionado.setAtributosBase(atributosBase);

        AtributosEntity atributos = new AtributosEntity();
        atributos.setPontosVida(atributosBase.getBaseVida());
        atributos.setPontosEnergia(atributosBase.getBaseEnergia());
        digimonSelecionado.setAtributos(atributos);

        AtributosModificadoresEntity atributosModificadores = new AtributosModificadoresEntity();
        digimonSelecionado.setAtributosModificadores(atributosModificadores);

        AtributosManipulaveisEntity atributosManipulaveis = new AtributosManipulaveisEntity();
        digimonSelecionado.setAtributosManipulaveis(atributosManipulaveis);

        AtributosElementosEntity atributosElementos = new AtributosElementosEntity();
        atributosElementos.setElementoPrimitivo(idElementoPrimitivoRookie);
        atributosElementos.setPontosElementoPrimitivo(1);
        digimonSelecionado.setAtributosElementos(atributosElementos);

        return digimonService.criarDigimon(digimonSelecionado);
    }
}
