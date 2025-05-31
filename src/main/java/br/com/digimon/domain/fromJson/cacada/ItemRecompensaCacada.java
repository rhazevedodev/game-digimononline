package br.com.digimon.domain.fromJson.cacada;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemRecompensaCacada {
    private String nome;
    @JsonProperty("quantidade_min")
    private int quantidadeMin;
    @JsonProperty("quantidade_max")
    private int quantidadeMax;
}
