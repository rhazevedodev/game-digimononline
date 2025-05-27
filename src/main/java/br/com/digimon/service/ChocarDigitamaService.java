package br.com.digimon.service;

import br.com.digimon.domain.*;
import br.com.digimon.domain.dto.ResponseChocarDigitamaDTO;
import br.com.digimon.domain.dto.ResponsePadraoDTO;
import br.com.digimon.domain.enums.EnumElementos;
import br.com.digimon.domain.fromJson.*;
import br.com.digimon.utils.AtributosBaseDigimons;
import br.com.digimon.utils.HeaderExtract;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@Service
public class ChocarDigitamaService {

    private TokenService tokenService;

    private DigimonService digimonService;

    private UsuarioService usuarioService;

    private JsonService jsonService;

    public ChocarDigitamaService(TokenService tokenService, DigimonService digimonService, UsuarioService usuarioService, JsonService jsonService) {
        this.tokenService = tokenService;
        this.digimonService = digimonService;
        this.usuarioService = usuarioService;
        this.jsonService = jsonService;
    }

    public ResponseChocarDigitamaDTO chocarDigitama(String idDigitama, HttpServletRequest request) {
        log.info("Chocando Digitama: {}", idDigitama);
        try {
            // Busca os dados das Digitamas
            ListaDigitamasJson listaDigitamasJson = jsonService.carregarDigitamas();
            ListaDigimonBabys1Json listaDigimonBabys1Json = jsonService.carregarDigiBabys1();

            List<String> possibleBaby1 = null;
            String selectedImage = null;
            int idBaby1 = 0;
            String selectedBaby1 = null;
            String elemento = null;

            ResponseChocarDigitamaDTO responseChocarDigitamaDTO = new ResponseChocarDigitamaDTO();

            for (DigitamaJson digitama : listaDigitamasJson.getDigitamas()) {
                if (digitama.getIdValue().equals(idDigitama)) {
                    possibleBaby1 = digitama.getPossibleBaby1();
                    break;
                }
            }

            if (possibleBaby1 != null && !possibleBaby1.isEmpty()) {
                // Sorteia um índice aleatório da lista
                int randomIndex = ThreadLocalRandom.current().nextInt(possibleBaby1.size());
                selectedBaby1 = possibleBaby1.get(randomIndex);
                responseChocarDigitamaDTO.setDigimonSorteado(selectedBaby1);

                // Busca a imagem do Digimon selecionado na lista de DigimonBaby1Json
                for (DigimonBaby1Json baby1 : listaDigimonBabys1Json.getDigimonsBaby1()) {
                    if (baby1.getNome().equals(selectedBaby1)) {
                        selectedImage = baby1.getImage();
                        responseChocarDigitamaDTO.setPathImage(selectedImage);
                        idBaby1 = baby1.getId();
                        elemento = baby1.getElemento();
                        break;
                    }
                }
            }

            if (elemento != null) {
                criarDigimon(idDigitama, request, idBaby1, elemento, selectedBaby1);
            }

            log.info("Imagem do Digimon selecionado: {}", selectedImage);
            return responseChocarDigitamaDTO;
        } catch (Exception e) {
            log.error("Erro ao chocar Digitama: {}", e.getMessage());
            throw e;
        }
    }

    public void criarDigimon(String idDigitama, HttpServletRequest request, int idBaby1, String elemento, String selectedBaby1) {
        String nomeUsuario = "";
        String jwt = HeaderExtract.extrairTokenDoHeader(request);
        nomeUsuario = tokenService.obterUsuarioPorToken(jwt);
        UsuarioEntity usuarioEntity = usuarioService.obterUsuarioPorNome(nomeUsuario);

        ResponsePadraoDTO primeiroAcesso = usuarioService.verificarPrimeiroAcesso(nomeUsuario);
        if (primeiroAcesso.getMensagem().equals("Primeiro acesso confirmado")) {
            usuarioEntity.setPrimeiroAcesso(false);
            usuarioService.atualizarUsuario(usuarioEntity);
        }

        DigimonEntity digimonSelecionado = new DigimonEntity();
        digimonSelecionado.setIdJogador(usuarioEntity.getId());
        digimonSelecionado.setIdDigitama(Integer.parseInt(idDigitama));
        digimonSelecionado.setIdBaby1(idBaby1);

        AtributosBaseEntity atributosBase = AtributosBaseDigimons.definirAtributosBaseDigimonsDigimonsBaby1(new AtributosBaseEntity(), selectedBaby1);
        digimonSelecionado.setAtributosBase(atributosBase);

        AtributosEntity atributos = new AtributosEntity();
        atributos.setPontosVida(atributosBase.getBaseVida());
        atributos.setPontosEnergia(atributosBase.getBaseEnergia());
        digimonSelecionado.setAtributos(atributos);

        AtributosModificadoresEntity atributosModificadores = new AtributosModificadoresEntity();
        digimonSelecionado.setAtributosModificadores(atributosModificadores);

        AtributosManipulaveisEntity atributosManipulaveis = new AtributosManipulaveisEntity();
        digimonSelecionado.setAtributosManipulaveis(atributosManipulaveis);

        int idElemento = EnumElementos.getIdElemento(elemento.toUpperCase());
        AtributosElementosEntity atributosElementos = new AtributosElementosEntity();
        atributosElementos.setElementoPrimitivo(idElemento);
        atributosElementos.setPontosElementoPrimitivo(1);
        digimonSelecionado.setAtributosElementos(atributosElementos);

        digimonService.criarDigimon(digimonSelecionado);
    }

}
