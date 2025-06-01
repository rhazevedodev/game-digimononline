package br.com.digimon.domain.fromJson.itens.fragmentosEvolucao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItensWrapper {
    private List<Itens> itens;
}
