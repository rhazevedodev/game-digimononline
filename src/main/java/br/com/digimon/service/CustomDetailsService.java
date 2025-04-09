package br.com.digimon.service;


import br.com.digimon.domain.UsuarioEntity;
import br.com.digimon.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioEntity usuario = usuarioRepository.findByUsername(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }

        System.out.println("==> NomeUsuario: " + usuario.getNomeUsuario());
        System.out.println("==> Senha (criptografada): " + usuario.getSenha());

        return new org.springframework.security.core.userdetails.User(
                usuario.getNomeUsuario(),
                usuario.getSenha(),
                Collections.emptyList()
        );
    }
}
