package br.com.digimon.utils;

import br.com.digimon.domain.fromJson.ListaDigimonBabys1Json;
import br.com.digimon.domain.fromJson.ListaDigitamasJson;
import br.com.digimon.domain.fromJson.ListaAtributosBaseDigimonsBaby1;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

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

    public static ListaAtributosBaseDigimonsBaby1 carregarAtributosBaseDigimonsBaby1() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File("src/main/resources/jsonMappings/atributosBaseDigimonsBaby1.json"), ListaAtributosBaseDigimonsBaby1.class);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar o arquivo JSON: " + e.getMessage(), e);
        }
    }
}
