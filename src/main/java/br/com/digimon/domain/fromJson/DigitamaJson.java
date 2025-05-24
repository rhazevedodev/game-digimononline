package br.com.digimon.domain.fromJson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DigitamaJson {
    private String nome;
    private String idValue;
    private List<String> possibleBaby1;
    private String image;
}
