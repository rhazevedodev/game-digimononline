package br.com.digimon.domain.usecase.impl;

import br.com.digimon.app.dto.CriarUsuarioDTO;
import br.com.digimon.domain.port.out.UsuarioRepositoryPort;
import br.com.digimon.domain.usecase.UsuarioUseCase;
import br.com.digimon.shared.exception.EmailJaExisteException;
import br.com.digimon.shared.exception.NomeUsuarioJaExisteException;
import br.com.digimon.shared.exception.UsuarioNaoExisteException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UsuarioUseCaseImpl implements UsuarioUseCase {

    @Autowired
    private UsuarioRepositoryPort usuarioRepositoryPort;

    @Override
    public void validacoesCriarUsuario(CriarUsuarioDTO criarUsuarioDTO) {
        log.info("Iniciando validações para criação de usuário: {}", criarUsuarioDTO.getEmail());

        if (verificarSeNomeUsuarioJaExiste(criarUsuarioDTO)) {
            throw new NomeUsuarioJaExisteException("Nome de usuário já cadastrado no sistema");
        }

        if(verificarSeEmailJaExiste(criarUsuarioDTO)) {
            throw new EmailJaExisteException("Email já cadastrado no sistema");
        }
    }

    @Override
    public void validarJogadorExiste(Long idJogador) {
        log.info("Validando se o jogador existe: {}", idJogador);
        if (!usuarioRepositoryPort.validarJogadorExiste(idJogador)) {
            throw new UsuarioNaoExisteException("Usuário não encontrado");
        }
    }

    private boolean verificarSeNomeUsuarioJaExiste(CriarUsuarioDTO criarUsuarioDTO) {
        log.info("Verificando se o nome de usuário já esta cadastrado: {}", criarUsuarioDTO.getNomeUsuario());
        return usuarioRepositoryPort.verificarSeNomeUsuarioJaExiste(criarUsuarioDTO.getNomeUsuario());
    }

    private boolean verificarSeEmailJaExiste(CriarUsuarioDTO criarUsuarioDTO) {
        log.info("Verificando se o email já esta cadastrado: {}", criarUsuarioDTO.getEmail());
        return usuarioRepositoryPort.verificarSeEmailJaExiste(criarUsuarioDTO.getEmail());
    }
}
