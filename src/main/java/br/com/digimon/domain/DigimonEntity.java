package br.com.digimon.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "digimon")
public class DigimonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O campo idJogador é obrigatório")
    private Long idJogador;

    private int idDigitama;

    private int idBaby1;

    private int idBaby2;

    private int idRookie;

    private int tierRookie;

    private int idChampion;

    private int tierChampion;

    private int idUltimate;

    private int tierUltimate;

    private int idMega;

    private int tierMega;

    @NotBlank(message = "O campo nome é obrigatório")
    @Size(min = 4, max = 20, message = "O campo nome deve ter entre 4 e 20 caracteres")
    private String nome = "default";

    private int nivel = 1;

    private int bits = 100;

    private int diamantes;

    private int pontosExperiencia;

    @Column(name = "poder_total")
    private int poderTotal = 0;

    @Embedded
    private AtributosEntity atributos;

    @Embedded
    private AtributosBaseEntity atributosBase;

    @Embedded
    private AtributosManipulaveisEntity atributosManipulaveis;

    @Embedded
    private AtributosModificadoresEntity atributosModificadores;

    @Embedded
    private AtributosElementosEntity atributosElementos;

    private LocalDate dataCadastro = LocalDate.now();

    private LocalDateTime dataUltimaAlteracao;

    private boolean bonusExperienciaAtivo;

    private boolean bonusBitsAtivo;

    private boolean sacrificado;
}
