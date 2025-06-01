package br.com.digimon.domain.fromJson.itens;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Itens {
    @JsonProperty("itens_fragmentos_evolucao")
    private Map<String, List<FragmentoEvolucao>> itensFragmentosEvolucao;

}
