package br.com.digimon.infra.repository.impl;

import br.com.digimon.domain.entity.UsuarioEntity;
import br.com.digimon.domain.port.out.UsuarioRepositoryPort;
import br.com.digimon.infra.repository.SpringDataUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public  class UsuarioRepositoryAdapter implements UsuarioRepositoryPort {

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
}
