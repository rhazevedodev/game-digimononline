package br.com.digimon.domain.fromJson.itens.outros;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItensOutros {
    @JsonProperty("cards_digimon")
    private List<Outros> cardsDigimon;
    @JsonProperty("itens_cura")
    private List<Outros> itensCura;
    @JsonProperty("itens_atributos")
    private List<Outros> itensAtributos;
    @JsonProperty("itens_diversos")
    private List<Outros> itensDiversos;

}
