package br.com.digimon.domain.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class AtributosBaseEntity {

    private int baseVida = 0;
    private int baseEnergia = 0;
    private int baseForca = 0;
    private int baseInteligencia = 0;
    private int baseAgilidade = 0;
    private int baseConhecimento = 0;
    private int baseResistencia = 0;

}
