package br.com.digimon.domain.fromJson.cacada;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cacada {
    private int id;
    private String nome;
    private String tier;
    private RequisitosCacada requisitos;
    @JsonProperty("duracao_segundos")
    private int duracaoSegundos;
    @JsonProperty("recompensas_possiveis")
    private RecompensasPossiveisCacada recompensasPossiveis;
    private CacadaAtiva cacadaAtiva;
}
