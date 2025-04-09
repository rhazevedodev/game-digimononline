package br.com.digimon.repository;

import br.com.digimon.domain.UsuarioEntity;

public interface UsuarioRepository {
    void criarUsuario(UsuarioEntity usuarioEntity);

    boolean verificarSeNomeUsuarioJaExiste(String nomeUsuario);

    boolean verificarSeEmailJaExiste(String email);

    boolean verificarPrimeiroAcesso(String usuario);

    UsuarioEntity findByUsername(String username);

    boolean validarJogadorExiste(Long idJogador);
}
