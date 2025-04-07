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
public class AtributosManipulaveisEntity {

    private int manipulaveisVida = 0;
    private int manipulaveisEnergia = 0;
    private int manipulaveisForca = 0;
    private int manipulaveisInteligencia = 0;
    private int manipulaveisAgilidade = 0;
    private int manipulaveisConhecimento = 0;
    private int manipulaveisResistencia = 0;

}
