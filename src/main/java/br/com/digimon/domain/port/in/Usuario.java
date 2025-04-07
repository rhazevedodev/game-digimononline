package br.com.digimon.domain.port.in;

import br.com.digimon.app.dto.CriarUsuarioDTO;

public interface Usuario {
    void criarUsuario(CriarUsuarioDTO criarUsuarioDTO);

}
