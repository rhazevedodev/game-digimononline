package br.com.digimon.domain.entity;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class AtributosElementosEntity {

    @NotNull
    private int elementoPrimitivo;

    private int pontosElementoPrimitivo;


}
