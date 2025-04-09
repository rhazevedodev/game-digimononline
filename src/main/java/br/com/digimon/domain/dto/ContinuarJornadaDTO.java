package br.com.digimon.domain.dto;

import br.com.digimon.domain.DigimonEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContinuarJornadaDTO {

    private DigimonEntity digimon;
    private String urlImagemDigimon;
}
