package br.com.digimon.domain.fromJson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AtributosBaseDigimonsBaby1 {
    private String nome;
    private int baseVida;
    private int baseEnergia;
    private int baseForca;
    private int baseInteligencia;
    private int baseAgilidade;
    private int baseConhecimento;
    private int baseResistencia;
}
