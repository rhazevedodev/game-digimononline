package br.com.digimon.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseListarDigievolucoes {
    private int idDigimonOrigem;
    private String digimonOrigem;
    private int idDigimonDestino;
    private String digimonDestino;
    private String pathImagemDigimonDestino;
    private int fragmentosDisponiveis;
    private int fragmentosNecessarios;
    private boolean itemEspecialNecessario;
    private int idItemEspecial;
    private String itemEspecial;
    private int emblemasDisponiveis;
    private int emblemasNecessarios;
    private int nivelMinimo;
    private int nivelAtual;
}
