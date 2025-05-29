package br.com.digimon.service;

import br.com.digimon.domain.DigimonEntity;
import br.com.digimon.domain.InventarioEntity;
import br.com.digimon.domain.dto.ResponseListarDigievolucoes;
import br.com.digimon.domain.fromJson.*;
import br.com.digimon.repository.DigimonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DigievolucaoService {

    @Autowired
    private DigimonRepository digimonRepository;

    @Autowired
    private DigimonService digimonService;

    @Autowired
    private InventarioService inventarioService;

    @Autowired
    private JsonService jsonService;

    public List<ResponseListarDigievolucoes> getEvolucoes(int idDigimonUsuario) {
        DigimonEntity digimon = digimonService.getDigimonById(Long.valueOf(idDigimonUsuario));
        String proxTier = digimonService.getProxTierDigimon((long) idDigimonUsuario);
        String tierAtual = switch (proxTier) {
            case "Baby 2" -> "Baby 1";
            case "Rookie" -> "Baby 2";
            case "Champion" -> "Rookie";
            case "Ultimate" -> "Champion";
            case "Mega" -> "Ultimate";
            case "Jogress" -> "Mega";
            default -> null;
        };

        if (tierAtual == null) {
            throw new RuntimeException("Tier atual não encontrado.");
        }

        List<EvolucaoJson> linhasEvolutivas = new ArrayList<>();
        List<InventarioEntity> inventarioFragmentos = inventarioService.getInventarioDoJogador(Long.valueOf(idDigimonUsuario), 4);
        List<InventarioEntity> inventarioEmblemas = inventarioService.getInventarioDoJogador(Long.valueOf(idDigimonUsuario), 5);
        List<ResponseListarDigievolucoes> evolucoesPossiveis = new ArrayList<>();

        switch (tierAtual){
            case "Baby 1":
                DigimonBaby1Json digimonBaby1 = jsonService.filtrarDigimonsBaby1PorId(digimon.getIdBaby1());
                linhasEvolutivas = jsonService.filtrarEvolucoesPorTierENome(tierAtual, digimonBaby1.getNome());
                break;
            case "Baby 2":
                DigimonBaby2Json digimonBaby2 = jsonService.filtrarDigimonsBaby2PorId(digimon.getIdBaby2());
                linhasEvolutivas = jsonService.filtrarEvolucoesPorTierENome(tierAtual, digimonBaby2.getNome());
                break;
            case "Rookie":
                DigimonRookieJson digimonRookie = jsonService.filtrarDigimonsRookiePorId(digimon.getIdRookie());
                linhasEvolutivas = jsonService.filtrarEvolucoesPorTierENome(tierAtual, digimonRookie.getNome());
                break;
            case "Champion":
                DigimonChampionJson digimonChampion = jsonService.filtrarDigimonsChampionPorId(digimon.getIdChampion());
                linhasEvolutivas = jsonService.filtrarEvolucoesPorTierENome(tierAtual, digimonChampion.getNome());
                break;
            case "Ultimate":
                DigimonUltimateJson digimonUltimate = jsonService.filtrarDigimonsUltimatePorId(digimon.getIdUltimate());
                linhasEvolutivas = jsonService.filtrarEvolucoesPorTierENome(tierAtual, digimonUltimate.getNome());
                break;
            case "Mega":
                DigimonMegaJson digimonMega = jsonService.filtrarDigimonsMegaPorId(digimon.getIdMega());
                linhasEvolutivas = jsonService.filtrarEvolucoesPorTierENome(tierAtual, digimonMega.getNome());
                break;
            case "Jogress":
                //linhasEvolutivas = digimonService.getDigimonsJogress();
                break;
        }

        for(EvolucaoJson evolucaoJson : linhasEvolutivas){
            ResponseListarDigievolucoes response = criarResponseEvolucao(evolucaoJson, inventarioFragmentos, inventarioEmblemas, digimon, tierAtual);
            evolucoesPossiveis.add(response);
        }

        return evolucoesPossiveis; // Aqui você deve implementar a lógica para buscar as evoluções do digimon com base no tierAtual
    }

    private ResponseListarDigievolucoes criarResponseEvolucao(EvolucaoJson evolucaoJson, List<InventarioEntity> inventarioFragmentos,
                                                                     List<InventarioEntity> inventarioEmblemas, DigimonEntity digimon, String tierAtual) {
        ResponseListarDigievolucoes response = new ResponseListarDigievolucoes();

        //EVOLUINDO PARA BABY 2
        if(tierAtual.equals("Baby 1")){
            //DE BABY 1
            response.setIdDigimonOrigem(digimon.getIdBaby1());
            response.setDigimonOrigem(evolucaoJson.getName());

            //PARA BABY2
            DigimonBaby2Json digimonBaby2 = jsonService.filtrarDigimonBaby2PorNome(evolucaoJson.getName());
            response.setIdDigimonDestino(digimonBaby2.getId());
            response.setDigimonDestino(digimonBaby2.getNome());
            response.setPathImagemDigimonDestino(digimonBaby2.getImage());
            response.setFragmentosNecessarios(evolucaoJson.getFragments());
            response.setFragmentosDisponiveis(0);
            response.setNivelMinimo(evolucaoJson.getLevel());
            response.setNivelAtual(digimon.getNivel());
        }
        //EVOLUINDO PARA ROOKIE
        if(tierAtual.equals("Baby 2")){
            //DE BABY 2
            response.setIdDigimonOrigem(digimon.getIdBaby2());
            response.setDigimonOrigem(evolucaoJson.getName());

            //PARA ROOKIE
            DigimonRookieJson digimonRookie = jsonService.filtrarDigimonRookiePorNome(evolucaoJson.getName());
            response.setIdDigimonDestino(digimonRookie.getId());
            response.setDigimonDestino(digimonRookie.getNome());
            response.setPathImagemDigimonDestino(digimonRookie.getImage());
            response.setFragmentosNecessarios(evolucaoJson.getFragments());
            response.setFragmentosDisponiveis(0);
            response.setNivelMinimo(evolucaoJson.getLevel());
            response.setNivelAtual(digimon.getNivel());
        }
        //EVOLUINDO PARA CHAMPION
        if(tierAtual.equals("Rookie")){
            //DE ROOKIE
            response.setIdDigimonOrigem(digimon.getIdRookie());
            response.setDigimonOrigem(evolucaoJson.getName());

            //PARA CHAMPION
            DigimonChampionJson digimonChampion = jsonService.filtrarDigimonChampionPorNome(evolucaoJson.getName());
            response.setIdDigimonDestino(digimonChampion.getId());
            response.setDigimonDestino(digimonChampion.getNome());
            response.setPathImagemDigimonDestino(digimonChampion.getImage());
            response.setFragmentosNecessarios(evolucaoJson.getFragments());
            response.setFragmentosDisponiveis(0);
            response.setNivelMinimo(evolucaoJson.getLevel());
            response.setNivelAtual(digimon.getNivel());
        }
        //EVOLUINDO PARA ULTIMATE
        if(tierAtual.equals("Champion")){
            //DE CHAMPION
            response.setIdDigimonOrigem(digimon.getIdChampion());
            response.setDigimonOrigem(evolucaoJson.getName());

            //PARA ULTIMATE
            DigimonUltimateJson digimonUltimate = jsonService.filtrarDigimonUltimatePorNome(evolucaoJson.getName());
            response.setIdDigimonDestino(digimonUltimate.getId());
            response.setDigimonDestino(digimonUltimate.getNome());
            response.setPathImagemDigimonDestino(digimonUltimate.getImage());
            response.setFragmentosNecessarios(evolucaoJson.getFragments());
            response.setFragmentosDisponiveis(0);
            response.setNivelMinimo(evolucaoJson.getLevel());
            response.setNivelAtual(digimon.getNivel());
        }
        //EVOLUINDO PARA MEGA
        if(tierAtual.equals("Ultimate")){
            //DE ULTIMATE
            response.setIdDigimonOrigem(digimon.getIdUltimate());
            response.setDigimonOrigem(evolucaoJson.getName());

            //PARA MEGA
            DigimonMegaJson digimonMega = jsonService.filtrarDigimonMegaPorNome(evolucaoJson.getName());
            response.setIdDigimonDestino(digimonMega.getId());
            response.setDigimonDestino(digimonMega.getNome());
            response.setPathImagemDigimonDestino(digimonMega.getImage());
            response.setFragmentosNecessarios(evolucaoJson.getFragments());
            response.setFragmentosDisponiveis(0);
            response.setNivelMinimo(evolucaoJson.getLevel());
            response.setNivelAtual(digimon.getNivel());
        }

        return response;
    }
}
