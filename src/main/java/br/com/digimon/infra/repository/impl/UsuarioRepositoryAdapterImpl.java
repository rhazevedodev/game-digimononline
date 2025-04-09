package br.com.digimon.infra.repository.impl;

import br.com.digimon.domain.entity.UsuarioEntity;
import br.com.digimon.domain.port.out.UsuarioRepositoryPort;
import br.com.digimon.infra.repository.jpa.SpringDataUsuarioRepository;
import br.com.digimon.shared.exception.UsuarioNaoExisteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
@Repository
public class UsuarioRepositoryAdapterImpl implements UsuarioRepositoryPort {

    @Autowired
    private  SpringDataUsuarioRepository springDataUsuarioRepository;

    @Override
    public void criarUsuario(UsuarioEntity usuarioEntity) {
        log.debug("Persistindo usuário no banco: {}", usuarioEntity.getEmail());
        springDataUsuarioRepository.save(usuarioEntity);
    }

    @Override
    public boolean verificarSeNomeUsuarioJaExiste(String nomeUsuario) {
        boolean exists = springDataUsuarioRepository.findByNomeUsuario(nomeUsuario).isPresent();
        if (exists) {
            log.debug("Nome de usuário já existe: {}", nomeUsuario);
        }
        return exists;
    }

    @Override
    public boolean verificarSeEmailJaExiste(String email) {
        boolean exists = springDataUsuarioRepository.findByEmail(email).isPresent();
        if (exists) {
            log.debug("Email já existe: {}", email);
        }
        return exists;
    }

    @Override
    public boolean verificarPrimeiroAcesso(String usuario) {
        Optional<UsuarioEntity> response = springDataUsuarioRepository.findByNomeUsuario(usuario);
        if (response.isEmpty()) {
            throw new UsuarioNaoExisteException("Usuário não encontrado: " + usuario);
        }
        return response.get().isPrimeiroAcesso();
    }

    @Override
    public UsuarioEntity findByUsername(String username) {
        Optional<UsuarioEntity> usuarioEntity = springDataUsuarioRepository.findByNomeUsuario(username);
        if (usuarioEntity.isPresent()) {
            return usuarioEntity.get();
        } else {
            throw new UsuarioNaoExisteException("Usuário não encontrado");
        }
    }

    @Override
    public boolean validarJogadorExiste(Long idJogador) {
        Optional<UsuarioEntity> usuarioEntity = springDataUsuarioRepository.findById(idJogador);
        if (usuarioEntity.isPresent()) {
            return true;
        } else {
            throw new UsuarioNaoExisteException("Usuário não encontrado");
        }
    }
}
