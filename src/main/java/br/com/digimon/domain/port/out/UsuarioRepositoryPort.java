package br.com.digimon.domain.port.out;

import br.com.digimon.domain.entity.UsuarioEntity;

public interface UsuarioRepositoryPort {
    void criarUsuario(UsuarioEntity usuarioEntity);
}
