package br.com.digimon.domain.fromJson.cacada;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecompensasPossiveisCacada {
    private List<PossivelItemRecompensaCacada> itens;
    @JsonProperty("exp_min")
    private int expMin;
    @JsonProperty("exp_max")
    private int expMax;
    @JsonProperty("bits_min")
    private int bitsMin;
    @JsonProperty("bits_max")
    private int bitsMax;

}
