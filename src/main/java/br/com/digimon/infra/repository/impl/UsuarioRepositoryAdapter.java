package br.com.digimon.infra.repository.impl;

import br.com.digimon.domain.entity.UsuarioEntity;
import br.com.digimon.domain.port.out.UsuarioRepositoryPort;
import br.com.digimon.infra.repository.SpringDataUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public  class UsuarioRepositoryAdapter implements UsuarioRepositoryPort {

    @Autowired
    private  SpringDataUsuarioRepository springDataUsuarioRepository;

    @Override
    public void criarUsuario(UsuarioEntity usuarioEntity) {
        springDataUsuarioRepository.save(usuarioEntity);
    }
}
