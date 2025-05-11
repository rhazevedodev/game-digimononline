package br.com.digimon.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AtributosBaseDTO {

    private int vida;
    private int energia;
    private int forca;
    private int inteligencia;
    private int agilidade;
    private int conhecimento;
    private int resistencia;

}
