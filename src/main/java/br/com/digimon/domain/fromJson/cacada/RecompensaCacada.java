package br.com.digimon.domain.fromJson.cacada;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecompensaCacada {
    private List<ItemRecompensaCacada> itens;
    private int exp;
    private int bits;
}
