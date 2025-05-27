package br.com.digimon.service;

import br.com.digimon.domain.fromJson.*;
import br.com.digimon.utils.GetByJsonUtils;
import org.springframework.cache.annotation.Cacheable;
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
}
