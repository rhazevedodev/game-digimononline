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
public class AtributosModificadoresEntity {

    private int modificadorVida = 0;

    private int modificadorEnergia = 0;

    private int modificadorForca = 0;

    private int modificadorInteligencia = 0;

    private int modificadorAgilidade = 0;

    private int modificadorConhecimento = 0;

    private int modificadorResistencia = 0;

}
