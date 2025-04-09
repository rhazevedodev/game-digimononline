package br.com.digimon.app.command;

import br.com.digimon.app.service.UsuarioService;
import br.com.digimon.domain.entity.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioCommand {

    @Autowired
    private UsuarioService usuarioService;

    public UsuarioEntity obterUsuarioPorNome(String nomeUsuario) {
        return usuarioService.obterUsuarioPorNome(nomeUsuario);
    }

    public boolean validarJogadorExiste(Long idJogador) {
        return usuarioService.validarJogadorExiste(idJogador);
    }


}
