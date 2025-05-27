package br.com.digimon.domain.fromJson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DigimonStageJson {
    private String name;
    private List<EvolucaoJson> evolvesTo;
}
