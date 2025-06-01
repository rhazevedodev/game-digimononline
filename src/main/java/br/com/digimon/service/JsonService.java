package br.com.digimon.service;

import br.com.digimon.domain.fromJson.*;
import br.com.digimon.domain.fromJson.cacada.Cacada;
import br.com.digimon.domain.fromJson.cacada.CacadaListWrapper;
import br.com.digimon.domain.fromJson.itens.FragmentoEvolucao;
import br.com.digimon.domain.fromJson.itens.Itens;
import br.com.digimon.domain.fromJson.itens.ItensWrapper;
import br.com.digimon.utils.GetByJsonUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JsonService {

    //@Cacheable(value = "digitamasCache")
    public ListaDigitamasJson carregarDigitamas(){
        return GetByJsonUtils.carregarDigitamas();
    }

    //@Cacheable(value = "digibabys1Cache")
    public ListaDigimonBabys1Json carregarDigiBabys1() {
        return GetByJsonUtils.carregarDigiBabys1();
    }

    //@Cacheable(value = "digibabys2Cache")
    public ListaDigimonBabys2Json carregarDigiBabys2() {
        return GetByJsonUtils.carregarDigiBabys2();
    }

    //@Cacheable(value = "atributosBaseDigimonsBaby1Cache")
    public ListaAtributosBaseDigimonsBaby1 carregarAtributosBaseDigimonsBaby1() {
        return GetByJsonUtils.carregarAtributosBaseDigimonsBaby1();
    }

    //@Cacheable(value = "digimonBaby1PorIdCache", key = "#id")
    public DigimonBaby1Json filtrarDigimonsBaby1PorId(int id){
        return GetByJsonUtils.filtrarDigimonsBaby1PorId(id);
    }

    //@Cacheable(value = "digimonBaby2PorIdCache", key = "#id")
    public DigimonBaby2Json filtrarDigimonsBaby2PorId(int id) {
        return GetByJsonUtils.filtrarDigimonsBaby2PorId(id);
    }

    //@Cacheable(value = "linhasEvolutivasCache", key = "#nomeDigimon")
    public List<EvolucaoJson> filtrarEvolucoesPorTierENome(String tierAtual, String nomeDigimon){
        return GetByJsonUtils.filtrarEvolucoesPorTierENome(tierAtual, nomeDigimon);
    }

    //@Cacheable(value = "digimonBaby2PorNomeCache", key = "#nome")
    public DigimonBaby2Json filtrarDigimonBaby2PorNome(String nome) {
        return GetByJsonUtils.filtrarDigimonBaby2PorNome(nome);
    }

    public DigimonRookieJson filtrarDigimonsRookiePorId(int idRookie) {
        return GetByJsonUtils.filtrarDigimonsRookiePorId(idRookie);
    }

    public DigimonRookieJson filtrarDigimonRookiePorNome(String name) {
        return GetByJsonUtils.filtrarDigimonRookiePorNome(name);
    }

    public ListaDigimonRookiesJson carregarDigiRookies() {
        return GetByJsonUtils.carregarDigiRookies();
    }

    public DigimonChampionJson filtrarDigimonsChampionPorId(int idChampion) {
        return GetByJsonUtils.filtrarDigimonsChampionPorId(idChampion);
    }

    public DigimonChampionJson filtrarDigimonChampionPorNome(String name) {
        return GetByJsonUtils.filtrarDigimonChampionPorNome(name);
    }

    public ListaDigimonChampionsJson carregarDigiChampions() {
        return GetByJsonUtils.carregarDigiChampions();
    }

    public ListaDigimonUltimatesJson carregarDigiUltimates() {
        return GetByJsonUtils.carregarDigiUltimates();
    }

    public ListaDigimonMegasJson carregarDigiMegas() {
        return GetByJsonUtils.carregarDigiMegas();
    }

    public DigimonUltimateJson filtrarDigimonsUltimatePorId(int idUltimate) {
        return GetByJsonUtils.filtrarDigimonsUltimatePorId(idUltimate);
    }

    public DigimonMegaJson filtrarDigimonsMegaPorId(int idMega) {
        return GetByJsonUtils.filtrarDigimonsMegaPorId(idMega);
    }

    public DigimonUltimateJson filtrarDigimonUltimatePorNome(String name) {
        return GetByJsonUtils.filtrarDigimonUltimatePorNome(name);
    }

    public DigimonMegaJson filtrarDigimonMegaPorNome(String name) {
        return GetByJsonUtils.filtrarDigimonMegaPorNome(name);
    }

    public CacadaListWrapper carregarCacadas() {
        return GetByJsonUtils.carregarCacadas();
    }

    public Cacada filtrarCacadaPorId(int id) {
        return GetByJsonUtils.filtrarCacadaPorId(id);
    }

    public List<FragmentoEvolucao> carregarFragmentosEvolucao(String nivel) {
        return GetByJsonUtils.filtrarFragmentosPorNivel(nivel);
    }

    public List<FragmentoEvolucao> listarTodosFragmentosEvolucao() {
        return GetByJsonUtils.listarTodosFragmentosEvolucao();
    }

    public ItensWrapper carregarItensFragmentosEvolucaoWrapper() {
        return GetByJsonUtils.carregarItensFragmentosEvolucaoWrapper();
    }
}
