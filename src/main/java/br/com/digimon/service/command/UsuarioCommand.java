package br.com.digimon.service.command;

import br.com.digimon.domain.UsuarioEntity;
import br.com.digimon.service.UsuarioService;
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
