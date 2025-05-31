package br.com.digimon.service;

import br.com.digimon.domain.CacadaEntity;
import br.com.digimon.domain.DigimonEntity;
import br.com.digimon.domain.UsuarioEntity;
import br.com.digimon.domain.fromJson.cacada.Cacada;
import br.com.digimon.domain.fromJson.cacada.CacadaAtiva;
import br.com.digimon.domain.fromJson.cacada.CacadaListWrapper;
import br.com.digimon.repository.CacadaRepository;
import br.com.digimon.utils.HeaderExtract;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class CacadaService {

    @Autowired
    private JsonService jsonService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private DigimonService digimonService;
    @Autowired
    private CacadaRepository cacadaRepository;

    public ResponseEntity<?> carregarCacadasJogador(Long idDigimon, HttpServletRequest request) {
        log.info("Carregando caçadas do jogador");
        String jwt = HeaderExtract.extrairTokenDoHeader(request);
        String nomeUsuario = tokenService.obterUsuarioPorToken(jwt);
        UsuarioEntity usuarioEntity = usuarioService.obterUsuarioPorNome(nomeUsuario);

        // Recupera o Digimon pelo id
        DigimonEntity digimon = digimonService.getDigimonById(idDigimon);

        // Carrega todas as caçadas disponíveis
        CacadaListWrapper cacadas = jsonService.carregarCacadas();

        // Filtra as caçadas que atendem aos requisitos do Digimon
        List<Cacada> cacadasValidas = cacadas.getCacadas().stream()
                .filter(cacada -> digimon.getNivel() >= cacada.getRequisitos().getNivelMinimo() &&
                        digimon.getPoderTotal() >= cacada.getRequisitos().getPoderTotal())
                .toList();

        // Busca as caçadas com recompensaResgatada = false para o idDigimon
        List<CacadaEntity> cacadasNaoResgatadas = cacadaRepository.findByIdDigimonAndRecompensaResgatadaFalse(idDigimon);

        // Verifica se o idCacada de cacadasNaoResgatadas coincide com cacadasValidas
        cacadasNaoResgatadas.forEach(cacadaNaoResgatada -> {
            cacadasValidas.stream()
                    .filter(cacadaValida -> cacadaValida.getId() == cacadaNaoResgatada.getIdCacada())
                    .findFirst()
                    .ifPresent(cacadaValida -> {
                        int segundosRestantes = (int) Duration.between(LocalDateTime.now(), cacadaNaoResgatada.getHoraResgateDisponivel()).getSeconds();
                        if(segundosRestantes < 0) {
                            segundosRestantes = 0; // Se o tempo já passou, define como 0
                        }
                        cacadaValida.setCacadaAtiva(new CacadaAtiva(segundosRestantes));
                    });
        });

        // Retorna as caçadas válidas
        return ResponseEntity.ok(cacadasValidas);
    }

    public ResponseEntity<?> iniciarCacada(Long idDigimon, Cacada cacada){
        log.info("Iniciando caçada para o Digimon com ID: {}", idDigimon);

        // Busca as caçadas com recompensaResgatada = false para o idDigimon
        List<CacadaEntity> cacadasNaoResgatadas = cacadaRepository.findByIdDigimonAndRecompensaResgatadaFalse(idDigimon);

        // Verifica se já existe uma caçada ativa com o mesmo idCacada
        boolean cacadaJaAtiva = cacadasNaoResgatadas.stream()
                .anyMatch(cacadaNaoResgatada -> cacadaNaoResgatada.getIdCacada() == (cacada.getId()));

        if (cacadaJaAtiva) {
            throw new IllegalStateException("Já existe uma caçada ativa com o ID: " + cacada.getId());
        }

        CacadaEntity cacadaEntity = new CacadaEntity();
        cacadaEntity.setIdDigimon(idDigimon);
        cacadaEntity.setIdCacada(cacada.getId());
        cacadaEntity.setSegundos(cacada.getDuracaoSegundos());
        cacadaEntity.setHoraResgateDisponivel(LocalDateTime.now().plusSeconds(cacada.getDuracaoSegundos()));
        cacadaEntity.setRecompensaResgatada(false);

        cacadaRepository.save(cacadaEntity);

        return ResponseEntity.ok("Cacada iniciada com sucesso!");
    }
}
