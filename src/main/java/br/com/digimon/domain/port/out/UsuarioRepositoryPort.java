package br.com.digimon.domain.port.out;

import br.com.digimon.domain.entity.UsuarioEntity;

public interface UsuarioRepositoryPort {
    void criarUsuario(UsuarioEntity usuarioEntity);

    boolean verificarSeNomeUsuarioJaExiste(String nomeUsuario);

    boolean verificarSeEmailJaExiste(String email);

    boolean veriricarPrimeiroAcesso(String usuario);

    UsuarioEntity findByUsername(String username);
}
