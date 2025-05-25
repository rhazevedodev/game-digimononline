package br.com.digimon.domain.fromJson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DigimonBaby1Json {
    private int id;
    private String nome;
    private String image;
    private String elemento;
}
