package br.com.digimon.utils;

import br.com.digimon.domain.fromJson.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class GetByJsonUtils {

    public static ListaDigitamasJson carregarDigitamas() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File("src/main/resources/jsonMappings/digitamas.json"), ListaDigitamasJson.class);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar o arquivo JSON: " + e.getMessage(), e);
        }
    }

    public static ListaDigimonBabys1Json carregarDigiBabys1() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File("src/main/resources/jsonMappings/enumDigimonsBaby1.json"), ListaDigimonBabys1Json.class);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar o arquivo JSON: " + e.getMessage(), e);
        }
    }

    public static ListaDigimonBabys2Json carregarDigiBabys2() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File("src/main/resources/jsonMappings/enumDigimonsBaby2.json"), ListaDigimonBabys2Json.class);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar o arquivo JSON: " + e.getMessage(), e);
        }
    }

    public static ListaDigimonRookiesJson carregarDigiRookies() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File("src/main/resources/jsonMappings/enumDigimonsRookies.json"), ListaDigimonRookiesJson.class);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar o arquivo JSON: " + e.getMessage(), e);
        }
    }

    public static ListaDigimonChampionsJson carregarDigiChampions() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File("src/main/resources/jsonMappings/enumDigimonsChampions.json"), ListaDigimonChampionsJson.class);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar o arquivo JSON: " + e.getMessage(), e);
        }
    }

    public static ListaAtributosBaseDigimonsBaby1 carregarAtributosBaseDigimonsBaby1() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File("src/main/resources/jsonMappings/atributosBaseDigimonsBaby1.json"), ListaAtributosBaseDigimonsBaby1.class);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar o arquivo JSON: " + e.getMessage(), e);
        }
    }

    public static DigimonBaby1Json filtrarDigimonsBaby1PorId(int id) {
        ListaDigimonBabys1Json lista = carregarDigiBabys1();
        for(DigimonBaby1Json digimon : lista.getDigimonsBaby1()) {
            if (digimon.getId() == id) {
                return digimon;
            }
        }
        throw new RuntimeException("ID de Digimon Baby1 inválido: " + id);
    }

    public static DigimonBaby2Json filtrarDigimonsBaby2PorId(int id) {
        ListaDigimonBabys2Json lista = carregarDigiBabys2();
        for(DigimonBaby2Json digimon : lista.getDigimonsBaby2()) {
            if (digimon.getId() == id) {
                return digimon;
            }
        }
        throw new RuntimeException("ID de Digimon Baby1 inválido: " + id);
    }
    public static DigimonRookieJson filtrarDigimonsRookiePorId(int idRookie) {
        ListaDigimonRookiesJson lista = carregarDigiRookies();
        for(DigimonRookieJson digimon : lista.getDigimonsRookies()) {
            if (digimon.getId() == idRookie) {
                return digimon;
            }
        }
        throw new RuntimeException("ID de Digimon Rookie inválido: " + idRookie);
    }

    public static DigimonChampionJson filtrarDigimonsChampionPorId(int idChampion) {
        ListaDigimonChampionsJson lista = carregarDigiChampions();
        for (DigimonChampionJson digimon : lista.getDigimonsChampions()) {
            if (digimon.getId() == idChampion) {
                return digimon;
            }
        }
        throw new RuntimeException("ID de Digimon Champion inválido: " + idChampion);
    }

    public static DigimonBaby2Json filtrarDigimonBaby2PorNome(String nome) {
        ListaDigimonBabys2Json lista = carregarDigiBabys2();
        for(DigimonBaby2Json digimon : lista.getDigimonsBaby2()) {
            if (digimon.getNome().equalsIgnoreCase(nome)) {
                return digimon;
            }
        }
        throw new RuntimeException("Nome de Digimon Baby2 inválido: " + nome);
    }

    public static DigimonRookieJson filtrarDigimonRookiePorNome(String name) {
        ListaDigimonRookiesJson lista = carregarDigiRookies();
        for (DigimonRookieJson digimon : lista.getDigimonsRookies()) {
            if (digimon.getNome().equalsIgnoreCase(name)) {
                return digimon;
            }
        }
        throw new RuntimeException("Nome de Digimon Rookie inválido: " + name);
    }

    public static DigimonChampionJson filtrarDigimonChampionPorNome(String name) {
        ListaDigimonChampionsJson lista = carregarDigiChampions();
        for (DigimonChampionJson digimon : lista.getDigimonsChampions()) {
            if (digimon.getNome().equalsIgnoreCase(name)) {
                return digimon;
            }
        }
        throw new RuntimeException("Nome de Digimon Champion inválido: " + name);
    }

    public static LinhasEvolutivasJson carregarLinhasEvolutivas() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File("src/main/resources/jsonMappings/linhasEvolutivas.json"), LinhasEvolutivasJson.class);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar o arquivo JSON: " + e.getMessage(), e);
        }
    }

    public static List<EvolucaoJson> filtrarEvolucoesPorTierENome(String tierAtual, String nomeDigimon) {
        LinhasEvolutivasJson linhasEvolutivas = carregarLinhasEvolutivas();

        for (StagesJson stage : linhasEvolutivas.getDigimons()) {
            if (stage.getStage().equalsIgnoreCase(tierAtual)) {
                for (DigimonStageJson digimon : stage.getDigimons()) {
                    if (digimon.getName().equalsIgnoreCase(nomeDigimon)) {
                        return digimon.getEvolvesTo();
                    }
                }
            }
        }

        throw new RuntimeException("Evoluções não encontradas para o Digimon: " + nomeDigimon + " no tier: " + tierAtual);
    }
}
