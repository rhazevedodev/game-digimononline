package br.com.digimon.service;

import br.com.digimon.domain.CacadaEntity;
import br.com.digimon.domain.DigimonEntity;
import br.com.digimon.domain.UsuarioEntity;
import br.com.digimon.domain.dto.GuardarNoInventarioRecompensaCacadaDTO;
import br.com.digimon.domain.fromJson.cacada.*;
import br.com.digimon.domain.fromJson.itens.fragmentosEvolucao.FragmentoEvolucao;
import br.com.digimon.domain.fromJson.itens.fragmentosEvolucao.ItensWrapper;
import br.com.digimon.domain.fromJson.itens.outros.ItensWrapperOutros;
import br.com.digimon.domain.fromJson.itens.outros.Outros;
import br.com.digimon.repository.CacadaRepository;
import br.com.digimon.utils.HeaderExtract;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    @Autowired
    private InventarioService inventarioService;

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
                        if (segundosRestantes < 0) {
                            segundosRestantes = 0; // Se o tempo já passou, define como 0
                        }
                        cacadaValida.setCacadaAtiva(new CacadaAtiva(segundosRestantes));
                    });
        });

        // Retorna as caçadas válidas
        return ResponseEntity.ok(cacadasValidas);
    }

    @Transactional
    public ResponseEntity<?> iniciarCacada(Long idDigimon, Cacada cacada) {
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

        DigimonEntity digimon = digimonService.getDigimonById(idDigimon);
        digimonService.atualizarEnergiaDigimon(digimon, -10); // Deduz energia do Digimon

        return ResponseEntity.ok("Cacada iniciada com sucesso!");
    }

    public RecompensaCacada resgatarRecompensa(Long idDigimon, int idCacada, HttpServletRequest request) {
        log.info("Resgatando recompensa da caçada");
        String jwt = HeaderExtract.extrairTokenDoHeader(request);
        String nomeUsuario = tokenService.obterUsuarioPorToken(jwt);
        UsuarioEntity usuarioEntity = usuarioService.obterUsuarioPorNome(nomeUsuario);

        //Filtrar a cacada pelo ID
        Cacada cacadaFiltrada = jsonService.filtrarCacadaPorId(idCacada);
        if (cacadaFiltrada == null) {
            throw new IllegalArgumentException("Caçada não encontrada com o ID: " + idCacada);
        }

        //Sorteando item que vai ser entregue
        List<PossivelItemRecompensaCacada> possivelItem = cacadaFiltrada.getRecompensasPossiveis().getItens();
        if (possivelItem == null || possivelItem.isEmpty()) {
            throw new IllegalStateException("Nenhum item disponível para sorteio.");
        }

        // Sorteia um item da lista de possíveis recompensas
        Random random = new Random();
        int indexSorteado = random.nextInt(possivelItem.size());
        PossivelItemRecompensaCacada itemSorteado = possivelItem.get(indexSorteado);

        // Sorteia a quantidade do item
        int quantidadeSorteada = random.nextInt(itemSorteado.getQuantidadeMax() - itemSorteado.getQuantidadeMin() + 1) + itemSorteado.getQuantidadeMin();

        // Sorteia a quantia de bits e exp
        int quantiaBitsSorteada = random.nextInt(cacadaFiltrada.getRecompensasPossiveis().getBitsMax() - cacadaFiltrada.getRecompensasPossiveis().getBitsMin() + 1) + cacadaFiltrada.getRecompensasPossiveis().getBitsMin();
        int quantiaExpSorteada = random.nextInt(cacadaFiltrada.getRecompensasPossiveis().getExpMax() - cacadaFiltrada.getRecompensasPossiveis().getExpMin() + 1) + cacadaFiltrada.getRecompensasPossiveis().getExpMin();

        // Sorteando o fragmento de evolução
        ItensWrapper itensEvolucaoWrapper = jsonService.carregarItensFragmentosEvolucaoWrapper();

        String tierRecompensa = digimonService.getProxTierDigimon(idDigimon);
        FragmentoEvolucao fragmentoSorteado = null;
        if(tierRecompensa.equals("Jogress")){
            List<FragmentoEvolucao> listaFragmentos = jsonService.listarTodosFragmentosEvolucao();
            listaFragmentos.size();
            int indexFragmentoSorteado = random.nextInt(listaFragmentos.size());
            fragmentoSorteado = listaFragmentos.get(indexFragmentoSorteado);

        } else {
            List<FragmentoEvolucao> listaFragmentos = jsonService.carregarFragmentosEvolucao(tierRecompensa);
            listaFragmentos.size();
            int indexFragmentoSorteado = random.nextInt(listaFragmentos.size());
            fragmentoSorteado = listaFragmentos.get(indexFragmentoSorteado);
        }
        int quantiaFragmentos = random.nextInt(5) + 1; // Sorteia entre 1 e 5 fragmentos
        System.out.println("Fragmento sorteado: " + (fragmentoSorteado != null ? fragmentoSorteado.getNome() : "Nenhum fragmento sorteado"));

        // Criando retorno
        RecompensaCacada recompensaCacada = new RecompensaCacada();
        List<ItemRecompensaCacada> itens = new ArrayList<>();
        List<GuardarNoInventarioRecompensaCacadaDTO> guardarNoInventario = new ArrayList<>();

        ItemRecompensaCacada itemRecompensaCacada = new ItemRecompensaCacada();
        itemRecompensaCacada.setNome(itemSorteado.getNome());
        itemRecompensaCacada.setQuantidade(quantidadeSorteada);
        itens.add(itemRecompensaCacada);
        guardarNoInventario.add(changingType(null, quantidadeSorteada, jsonService.carregarItemPorNome(itemSorteado.getNome())));

        ItemRecompensaCacada itemFragmento = new ItemRecompensaCacada();
        itemFragmento.setNome(fragmentoSorteado.getNome());
        itemFragmento.setQuantidade(quantiaFragmentos);
        itens.add(itemFragmento);
        guardarNoInventario.add(changingType(fragmentoSorteado, quantiaFragmentos,null));

        recompensaCacada.setItens(itens);
        recompensaCacada.setExp(quantiaExpSorteada);
        recompensaCacada.setBits(quantiaBitsSorteada);

        //Atualiza dados digimon
        digimonService.atualizarDigimonExpBits(idDigimon, quantiaExpSorteada, quantiaBitsSorteada);

        //Dando 1 ponto digital pela ação de concluir caçada
        usuarioEntity.setPontosDigitais(usuarioEntity.getPontosDigitais() + 1);
        usuarioService.updateUsuario(usuarioEntity);

        //Guardando no inventario
        inventarioService.adicionarItemAoInventario(idDigimon, guardarNoInventario);

        //Finalizando caçada
        CacadaEntity cacadaEntity = cacadaRepository.findByIdDigimonAndIdCacadaAndRecompensaResgatadaFalse(idDigimon, idCacada);
        if (cacadaEntity == null) {
            throw new IllegalStateException("Caçada não encontrada ou já resgatada.");
        }
        atualizarCacada(cacadaEntity);

        return recompensaCacada;
    }

    private GuardarNoInventarioRecompensaCacadaDTO changingType(FragmentoEvolucao fragmentoSorteado, int quantidade, Outros outros){
        GuardarNoInventarioRecompensaCacadaDTO changing = new GuardarNoInventarioRecompensaCacadaDTO();
        if(fragmentoSorteado == null) {
            changing.setId(outros.getId());
            changing.setIdCategoria(outros.getIdCategoria());
            changing.setNome(outros.getNome());
            changing.setDescricao(outros.getDescricao());
            changing.setValorCompra(outros.getValorCompra());
            changing.setValorVenda(outros.getValorVenda());
            changing.setPodeVender(outros.isPodeVender());
            changing.setPodeTrocar(outros.isPodeTrocar());
            changing.setGlobal(outros.isGlobal());
            changing.setQuantidade(quantidade);
        } else {
            changing.setId(fragmentoSorteado.getId());
            changing.setIdCategoria(fragmentoSorteado.getIdCategoria());
            changing.setNome(fragmentoSorteado.getNome());
            changing.setDescricao(fragmentoSorteado.getDescricao());
            changing.setValorCompra(fragmentoSorteado.getValorCompra());
            changing.setValorVenda(fragmentoSorteado.getValorVenda());
            changing.setPodeVender(fragmentoSorteado.isPodeVender());
            changing.setPodeTrocar(fragmentoSorteado.isPodeTrocar());
            changing.setGlobal(fragmentoSorteado.isGlobal());
            changing.setQuantidade(quantidade);
        }
        return changing;
    }

    private void atualizarCacada(CacadaEntity cacada) {
        cacada.setRecompensaResgatada(true);
        cacada.setUltimaAlteracao(LocalDateTime.now());
        cacadaRepository.save(cacada);
    }
}
