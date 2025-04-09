package br.com.digimon.app.service;

import br.com.digimon.app.command.TokenCommand;
import br.com.digimon.app.command.UsuarioCommand;
import br.com.digimon.app.dto.SelecaoDigimonDTO;
import br.com.digimon.domain.entity.*;
import br.com.digimon.domain.port.in.Digimon;
import br.com.digimon.domain.port.out.DigimonRepositoryPort;
import br.com.digimon.shared.enums.EnumDigimonRookie;
import br.com.digimon.shared.enums.EnumElementos;
import br.com.digimon.shared.exception.ApelidoDigimonJaEscolhidoException;
import br.com.digimon.shared.util.AtributosBaseDigimons;
import br.com.digimon.shared.util.HeaderExtract;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DigimonService implements Digimon {

    @Autowired
    private TokenCommand tokenCommand;

    @Autowired
    private UsuarioCommand usuarioCommand;

    @Autowired
    private DigimonRepositoryPort digimonRepositoryPort;

    public ResponseEntity<?> selecionarDigimon(SelecaoDigimonDTO selecaoDigimonDTO, HttpServletRequest request) {
        log.info("Selecionando Digimon: {}", selecaoDigimonDTO.getNomeDigimon());
        try{
            String jwt = HeaderExtract.extrairTokenDoHeader(request);
            String nomeUsuario = tokenCommand.obterUsuarioPorToken(jwt);
            UsuarioEntity usuarioEntity = usuarioCommand.obterUsuarioPorNome(nomeUsuario);

            DigimonEntity digimonSelecionado = new DigimonEntity();
            digimonSelecionado.setIdJogador(usuarioEntity.getId());
            digimonSelecionado.setIdRookie(Integer.parseInt(getIdByDescricao(selecaoDigimonDTO.getNomeDigimon())));

            boolean apelidoJaEscolhido = verificarSeApelidoDigimonJaExiste(selecaoDigimonDTO.getApelidoDigimon());

            if (apelidoJaEscolhido) {
                throw new ApelidoDigimonJaEscolhidoException("Esse apelido de digimon já foi escolhido, escolha outro!");
            } else {
                digimonSelecionado.setNome(selecaoDigimonDTO.getApelidoDigimon());
            }

            DigimonEntity novoDigimon = selecionarDigimonCompleto(digimonSelecionado);

            log.info("Digimon selecionado com sucesso para o usuário: {}", nomeUsuario);
            return ResponseEntity.ok(novoDigimon);
        } catch (Exception e) {
            log.error("Erro ao selecionar o Digimon: {}", e.getMessage());
            return ResponseEntity.status(500).body("Erro ao selecionar o Digimon");
        }
    }

    public String getIdByDescricao(String descricao) {
        return EnumDigimonRookie.getIdByDescricao(descricao);
    }

    public boolean verificarSeApelidoDigimonJaExiste(String apelidoDigimon) {
        return digimonRepositoryPort.verificarSeApelidoDigimonJaExiste(apelidoDigimon);
    }

    public DigimonEntity selecionarDigimonCompleto(DigimonEntity digimonSelecionado) {
        usuarioCommand.validarJogadorExiste(digimonSelecionado.getIdJogador());
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

        return digimonRepositoryPort.criarDigimon(digimonSelecionado);
    }
}
