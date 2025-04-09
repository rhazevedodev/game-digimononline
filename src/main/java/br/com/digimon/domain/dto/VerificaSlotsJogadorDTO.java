package br.com.digimon.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VerificaSlotsJogadorDTO {

    private Long idJogador;
    private int slotsDigimon;
}
