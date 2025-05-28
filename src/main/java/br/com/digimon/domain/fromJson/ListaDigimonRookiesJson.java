package br.com.digimon.domain.fromJson;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListaDigimonRookiesJson {
    @JsonProperty("digimonsRookies")
    private List<DigimonRookieJson> digimonsRookies;
}
