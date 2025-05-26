package br.com.digimon.utils;

import br.com.digimon.domain.fromJson.*;
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

    public static ListaDigimonBabys2Json carregarDigiBabys2() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File("src/main/resources/jsonMappings/enumDigimonsBaby2.json"), ListaDigimonBabys2Json.class);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar o arquivo JSON: " + e.getMessage(), e);
        }
    }

    public static DigimonBaby1Json      filtrarDigimonsBaby1PorId(int id) {
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
}
