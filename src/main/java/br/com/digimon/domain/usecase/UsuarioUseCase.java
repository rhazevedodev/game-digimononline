package br.com.digimon.domain.usecase;

import br.com.digimon.app.dto.CriarUsuarioDTO;

public interface UsuarioUseCase {

    void validacoesCriarUsuario(CriarUsuarioDTO criarUsuarioDTO);
}
