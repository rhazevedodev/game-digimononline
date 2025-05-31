package br.com.digimon.domain.dto;

import br.com.digimon.domain.fromJson.cacada.Cacada;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestIniciarCacadaDTO {
    private Long idDigimon;
    private Cacada cacadaSelecionada;
}
