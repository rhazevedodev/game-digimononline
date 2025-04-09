package br.com.digimon.app.service;

import br.com.digimon.app.dto.CriarUsuarioDTO;
import br.com.digimon.app.dto.RespostaPadraoDTO;
import br.com.digimon.domain.entity.UsuarioEntity;
import br.com.digimon.domain.port.in.Usuario;
import br.com.digimon.domain.port.out.UsuarioRepositoryPort;
import br.com.digimon.domain.usecase.UsuarioUseCase;
import br.com.digimon.shared.exception.EmailJaExisteException;
import br.com.digimon.shared.exception.NomeUsuarioJaExisteException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UsuarioService implements Usuario {

    @Autowired
    private UsuarioRepositoryPort usuarioRepositoryPort;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioUseCase usuarioUseCase;

    @Override
    @Transactional
    public void criarUsuario(CriarUsuarioDTO criarUsuarioDTO) {
        log.info("Iniciando criação de usuário: {}", criarUsuarioDTO.getEmail());

        usuarioUseCase.validacoesCriarUsuario(criarUsuarioDTO);

        String senhaCriptografada = passwordEncoder.encode(criarUsuarioDTO.getSenha());
        criarUsuarioDTO.setSenha(senhaCriptografada);

        usuarioRepositoryPort.criarUsuario(montarObjetoUsuario(criarUsuarioDTO));

        log.info("Usuário criado com sucesso: {}", criarUsuarioDTO.getEmail());
    }

    @Override
    public RespostaPadraoDTO veriricarPrimeiroAcesso(String usuario) {
        log.info("Verificando primeiro acesso do usuário: {}", usuario);
        boolean primeiroAcesso = usuarioRepositoryPort.veriricarPrimeiroAcesso(usuario);
        if(primeiroAcesso){
            log.info("Primeiro acesso confirmado para o usuário: {}", usuario);
            return new RespostaPadraoDTO("Primeiro acesso confirmado");
        } else {
            log.info("Não é o primeiro acesso para o usuário: {}", usuario);
            return new RespostaPadraoDTO("Não é o primeiro acesso");
        }
    }


    private UsuarioEntity montarObjetoUsuario(CriarUsuarioDTO criarUsuarioDTO) {
        log.info("Montando objeto UsuarioEntity a partir de CriarUsuarioDTO: {}", criarUsuarioDTO.getEmail());
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setNomeUsuario(criarUsuarioDTO.getNomeUsuario());
        usuarioEntity.setEmail(criarUsuarioDTO.getEmail());
        usuarioEntity.setSenha(criarUsuarioDTO.getSenha());
        usuarioEntity.setDataNascimento(criarUsuarioDTO.getDataNascimento());
        return usuarioEntity;
    }


}
