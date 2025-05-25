package br.com.digimon.service;


import br.com.digimon.domain.UsuarioEntity;
import br.com.digimon.domain.dto.CriarUsuarioDTO;
import br.com.digimon.domain.dto.ResponsePadraoDTO;
import br.com.digimon.exception.EmailJaExisteException;
import br.com.digimon.exception.NomeUsuarioJaExisteException;
import br.com.digimon.exception.UsuarioNaoExisteException;
import br.com.digimon.repository.UsuarioRepository;
import br.com.digimon.utils.HeaderExtract;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepositoryPort;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private DigimonService digimonService;

    private TokenService tokenService;

    public UsuarioService(DigimonService digimonService, TokenService tokenService) {
        this.digimonService = digimonService;
        this.tokenService = tokenService;
    }

    @Transactional
    public void criarUsuario(CriarUsuarioDTO criarUsuarioDTO) {
        log.info("Iniciando criação de usuário: {}", criarUsuarioDTO.getEmail());

        validacoesCriarUsuario(criarUsuarioDTO);

        String senhaCriptografada = passwordEncoder.encode(criarUsuarioDTO.getSenha());
        criarUsuarioDTO.setSenha(senhaCriptografada);

        usuarioRepositoryPort.criarUsuario(montarObjetoUsuario(criarUsuarioDTO));

        log.info("Usuário criado com sucesso: {}", criarUsuarioDTO.getEmail());
    }

    public ResponsePadraoDTO verificarPrimeiroAcesso(String usuario) {
        log.info("Verificando primeiro acesso do usuário: {}", usuario);
        boolean primeiroAcesso = usuarioRepositoryPort.verificarPrimeiroAcesso(usuario);
        if(primeiroAcesso){
            log.info("Primeiro acesso confirmado para o usuário: {}", usuario);
            return new ResponsePadraoDTO("Primeiro acesso confirmado");
        } else {
            log.info("Não é o primeiro acesso para o usuário: {}", usuario);
            return new ResponsePadraoDTO("Não é o primeiro acesso");
        }
    }

    public void atualizarUsuario(UsuarioEntity usuario){
        usuarioRepositoryPort.salvarUsuario(usuario);
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

    public UsuarioEntity obterUsuarioPorNome(String nomeUsuario) {
        log.info("Buscando usuário pelo nome: {}", nomeUsuario);
        return usuarioRepositoryPort.findByUsername(nomeUsuario);
    }

    public boolean validarJogadorExiste(Long idJogador) {
        log.info("Validando se o jogador existe: {}", idJogador);
        if (!usuarioRepositoryPort.validarJogadorExiste(idJogador)) {
            throw new UsuarioNaoExisteException("Usuário não encontrado");
        }
        return true;
    }

    public void validacoesCriarUsuario(CriarUsuarioDTO criarUsuarioDTO) {
        log.info("Iniciando validações para criação de usuário: {}", criarUsuarioDTO.getEmail());

        if (verificarSeNomeUsuarioJaExiste(criarUsuarioDTO)) {
            throw new NomeUsuarioJaExisteException("Nome de usuário já cadastrado no sistema");
        }

        if(verificarSeEmailJaExiste(criarUsuarioDTO)) {
            throw new EmailJaExisteException("Email já cadastrado no sistema");
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

    public int carregarPontosDigitais(HttpServletRequest request) {
        try {
            String jwt = HeaderExtract.extrairTokenDoHeader(request);
            String nomeUsuario = tokenService.obterUsuarioPorToken(jwt);
            UsuarioEntity usuarioEntity = usuarioRepositoryPort.findByUsername(nomeUsuario);
            return usuarioEntity.getPontosDigitais();
        } catch (Exception e) {
            log.error("Erro ao carregar pontos digitais: {}", e.getMessage());
            throw new RuntimeException("Erro ao carregar pontos digitais: " + e.getMessage());
        }
    }


    public UsuarioEntity getJogadorById(Long idJogador) {
        log.info("Buscando jogador pelo ID: {}", idJogador);
        Optional<UsuarioEntity> usuarioOptional = usuarioRepositoryPort.findById(idJogador);
        if (usuarioOptional.isPresent()) {
            return usuarioOptional.get();
        } else {
            throw new UsuarioNaoExisteException("Usuário não encontrado");
        }
    }
}
