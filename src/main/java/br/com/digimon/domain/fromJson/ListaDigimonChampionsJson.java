package br.com.digimon.domain.fromJson;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListaDigimonChampionsJson {
    @JsonProperty("digimonsChampions")
    private List<DigimonChampionJson> digimonsChampions;
}
