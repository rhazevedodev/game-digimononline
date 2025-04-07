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
public class AtributosEntity {

    private int pontosVida = 0;
    private int pontosEnergia = 0;

    private int pontosForca= 0;

    private int pontosInteligencia= 0;

    private int pontosAgilidade= 0;

    private int pontosConhecimento= 0;

    private int pontosResistencia= 0;

}
