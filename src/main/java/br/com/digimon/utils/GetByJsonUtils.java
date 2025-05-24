package br.com.digimon.utils;

import br.com.digimon.domain.fromJson.DigitamasJson;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class GetByJsonUtils {

    private static final String JSON_PATH = "src/main/resources/jsonMappings/digitamas.json";

    public static DigitamasJson carregarDigitamas() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File(JSON_PATH), DigitamasJson.class);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar o arquivo JSON: " + e.getMessage(), e);
        }
    }
}
