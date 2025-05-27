package br.com.digimon.domain.fromJson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StagesJson {
    private String stage;
    private List<DigimonStageJson> digimons;
}
