package br.com.digimon.domain.fromJson;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DigitamasJson {

    @JsonProperty("digitamas")
    private List<DigitamaJson> digitamas;
}
