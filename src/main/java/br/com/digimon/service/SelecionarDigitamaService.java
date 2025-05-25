package br.com.digimon.service;

import br.com.digimon.domain.dto.ResponseSelecaoDigitamaDTO;
import br.com.digimon.domain.dto.RequestSelecaoDigitamaDTO;
import br.com.digimon.domain.fromJson.DigitamaJson;
import br.com.digimon.domain.fromJson.ListaDigitamasJson;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SelecionarDigitamaService {

    private DigimonService digimonService;

    private UsuarioService usuarioService;

    private TokenService tokenService;

    public SelecionarDigitamaService(DigimonService digimonService, UsuarioService usuarioService, TokenService tokenService) {
        this.digimonService = digimonService;
        this.usuarioService = usuarioService;
        this.tokenService = tokenService;
    }

    /**
     * Método para selecionar um Digitama.
     *
     * @param requestSelecaoDigitamaDTO DTO com os dados do Digitama a ser selecionado.
     * @param httpServletRequest Requisição HTTP.
     * @return O Digitama selecionado.
     */
    public ResponseSelecaoDigitamaDTO selecionarDigitama(RequestSelecaoDigitamaDTO requestSelecaoDigitamaDTO, HttpServletRequest httpServletRequest) {
        log.info("Selecionando Digitama: ");
        String nomeUsuario = "";
        try {

            // Busca os dados das Digitamas
            ListaDigitamasJson listaDigitamasJson = digimonService.getDigitamasByJson();

            ResponseSelecaoDigitamaDTO responseSelecaoDigitamaDTO = new ResponseSelecaoDigitamaDTO();

            listaDigitamasJson.getDigitamas().size();
            for (DigitamaJson digitama : listaDigitamasJson.getDigitamas()) {
                if (digitama.getIdValue().equals(requestSelecaoDigitamaDTO.getIdDigitama())) {
                    responseSelecaoDigitamaDTO.setNomeDigitama(digitama.getNome());
                    responseSelecaoDigitamaDTO.setPathDigitama(digitama.getImage());
                    break;
                }
            }

            return responseSelecaoDigitamaDTO;
        } catch (Exception e) {
            log.error("Erro ao selecionar Digitama: {}", e.getMessage());
            return new ResponseSelecaoDigitamaDTO();
        }
    }
}
