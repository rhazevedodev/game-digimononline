package br.com.digimon.utils;

import br.com.digimon.domain.fromJson.*;
import br.com.digimon.domain.fromJson.cacada.Cacada;
import br.com.digimon.domain.fromJson.cacada.CacadaListWrapper;
import br.com.digimon.domain.fromJson.itens.fragmentosEvolucao.FragmentoEvolucao;
import br.com.digimon.domain.fromJson.itens.fragmentosEvolucao.Itens;
import br.com.digimon.domain.fromJson.itens.fragmentosEvolucao.ItensWrapper;
import br.com.digimon.domain.fromJson.itens.outros.ItensWrapperOutros;
import br.com.digimon.domain.fromJson.itens.outros.Outros;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

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

    public static ListaDigimonUltimatesJson carregarDigiUltimates() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File("src/main/resources/jsonMappings/enumDigimonsUltimates.json"), ListaDigimonUltimatesJson.class);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar o arquivo JSON: " + e.getMessage(), e);
        }
    }

    public static ListaDigimonMegasJson carregarDigiMegas() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File("src/main/resources/jsonMappings/enumDigimonsMegas.json"), ListaDigimonMegasJson.class);
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

    public static DigimonUltimateJson filtrarDigimonsUltimatePorId(int idUltimate) {
        ListaDigimonUltimatesJson lista = carregarDigiUltimates();
        for (DigimonUltimateJson digimon : lista.getDigimonsUltimates()) {
            if (digimon.getId() == idUltimate) {
                return digimon;
            }
        }
        throw new RuntimeException("ID de Digimon Ultimate inválido: " + idUltimate);
    }

    public static DigimonMegaJson filtrarDigimonsMegaPorId(int idMega) {
        ListaDigimonMegasJson lista = carregarDigiMegas();
        for (DigimonMegaJson digimon : lista.getDigimonsMegas()) {
            if (digimon.getId() == idMega) {
                return digimon;
            }
        }
        throw new RuntimeException("ID de Digimon Mega inválido: " + idMega);
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

    public static DigimonUltimateJson filtrarDigimonUltimatePorNome(String name) {
        ListaDigimonUltimatesJson lista = carregarDigiUltimates();
        for (DigimonUltimateJson digimon : lista.getDigimonsUltimates()) {
            if (digimon.getNome().equalsIgnoreCase(name)) {
                return digimon;
            }
        }
        throw new RuntimeException("Nome de Digimon Ultimate inválido: " + name);
    }

    public static DigimonMegaJson filtrarDigimonMegaPorNome(String name) {
        ListaDigimonMegasJson lista = carregarDigiMegas();
        for (DigimonMegaJson digimon : lista.getDigimonsMegas()) {
            if (digimon.getNome().equalsIgnoreCase(name)) {
                return digimon;
            }
        }
        throw new RuntimeException("Nome de Digimon Mega inválido: " + name);
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

    public static CacadaListWrapper carregarCacadas() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File("src/main/resources/jsonMappings/cacadasDisponiveis.json"), CacadaListWrapper.class);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar o arquivo JSON: " + e.getMessage(), e);
        }
    }

    public static Cacada filtrarCacadaPorId(int id) {
        CacadaListWrapper cacadas = carregarCacadas();
        return cacadas.getCacadas().stream()
                .filter(cacada -> cacada.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Caçada com ID " + id + " não encontrada."));
    }


    public static List<FragmentoEvolucao> filtrarFragmentosPorNivel(String nivel) {
        // Carrega os itens e fragmentos de evolução
        ItensWrapper itensContainer = carregarItensFragmentosEvolucaoWrapper();

        if (itensContainer == null || itensContainer.getItens() == null || itensContainer.getItens().isEmpty()) {
            throw new RuntimeException("Dados de itens não carregados corretamente.");
        }

        // Considerando que só tem um item no array 'itens'
        Itens item = itensContainer.getItens().get(0);

        // Obtém o mapa de níveis para listas de fragmentos
        Map<String, List<FragmentoEvolucao>> fragmentosPorNivel = item.getItensFragmentosEvolucao();

        // Atenção: no JSON as chaves são tipo "Baby 2", "Rookie", "Champion" (com maiúsculas e espaços),
        // então cuidado ao usar toLowerCase() — é melhor comparar sem mudar a caixa.

        List<FragmentoEvolucao> fragmentos = fragmentosPorNivel.get(nivel);

        if (fragmentos == null) {
            throw new RuntimeException("Nível inválido ou não encontrado: " + nivel);
        }

        return fragmentos;
    }

    public static List<FragmentoEvolucao> listarTodosFragmentosEvolucao() {
        // Carrega o wrapper de itens
        ItensWrapper itensWrapper = carregarItensFragmentosEvolucaoWrapper();

        // Lista para armazenar todos os fragmentos
        List<FragmentoEvolucao> todosFragmentos = new ArrayList<>();

        // Itera sobre todos os itens no wrapper
        for (Itens item : itensWrapper.getItens()) {
            // Itera sobre os valores do mapa e adiciona os fragmentos à lista
            item.getItensFragmentosEvolucao().values().forEach(todosFragmentos::addAll);
        }

        return todosFragmentos;
    }

    public static ItensWrapper carregarItensFragmentosEvolucaoWrapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File("src/main/resources/jsonMappings/itensFragmentosEvolucao.json"), ItensWrapper.class);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar o arquivo JSON: " + e.getMessage(), e);
        }
    }

    public static ItensWrapperOutros carregarItens() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File("src/main/resources/jsonMappings/itens.json"), ItensWrapperOutros.class);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar o arquivo JSON: " + e.getMessage(), e);
        }
    }

    public static Outros carregarItemPorNome(String nomeItem) {
        ObjectMapper objectMapper = new ObjectMapper();

            // Carrega o wrapper de itens
            ItensWrapperOutros itensWrapper = carregarItens();
            List<Outros> cardsDigimon = itensWrapper.getItens().get(0).getCardsDigimon();
            List<Outros> itensCura = itensWrapper.getItens().get(0).getItensCura();
            List<Outros> itensAtributos = itensWrapper.getItens().get(0).getItensAtributos();
            List<Outros> itensDiversos = itensWrapper.getItens().get(0).getItensDiversos();

            // Procura o item pelo nome em todas as listas
            return Stream.of(cardsDigimon, itensCura, itensAtributos, itensDiversos)
                    .flatMap(List::stream)
                    .filter(item -> item.getNome().equalsIgnoreCase(nomeItem))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Item com o nome '" + nomeItem + "' não encontrado."));

    }
}
