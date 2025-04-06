package br.com.digimon.app.usecase;

import br.com.digimon.app.dto.CriarUsuarioDTO;
import br.com.digimon.domain.entity.UsuarioEntity;
import br.com.digimon.domain.port.in.Usuario;
import br.com.digimon.domain.port.out.UsuarioRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioUseCase implements Usuario {

    @Autowired
    private UsuarioRepositoryPort usuarioRepositoryPort;

    @Override
    public void criarUsuario(CriarUsuarioDTO criarUsuarioDTO) {
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setNomeUsuario(criarUsuarioDTO.getNomeUsuario());
        usuarioEntity.setEmail(criarUsuarioDTO.getEmail());
        usuarioEntity.setSenha(criarUsuarioDTO.getSenha());
        usuarioEntity.setDataNascimento(criarUsuarioDTO.getDataNascimento());
        usuarioRepositoryPort.criarUsuario(usuarioEntity);
    }
}
