package br.com.digimon.domain.port.in;

import br.com.digimon.app.dto.CriarUsuarioDTO;
import br.com.digimon.app.dto.RespostaPadraoDTO;

public interface Usuario {
    void criarUsuario(CriarUsuarioDTO criarUsuarioDTO);
    RespostaPadraoDTO verificarPrimeiroAcesso(String usuario);

}
