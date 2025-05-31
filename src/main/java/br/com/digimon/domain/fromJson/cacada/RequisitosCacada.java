package br.com.digimon.domain.fromJson.cacada;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequisitosCacada {
    @JsonProperty("nivel_minimo")
    private int nivelMinimo;
    @JsonProperty("poder_total")
    private int poderTotal;
}
