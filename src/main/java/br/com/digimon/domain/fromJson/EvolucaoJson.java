package br.com.digimon.domain.fromJson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EvolucaoJson {
    private String name;
    private int level;
    private int fragments;
    private String item;
    private Integer itemQuantity;
}
