package br.com.digimon.domain.fromJson.itens.outros;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItensWrapperOutros {
    private List<ItensOutros> itens;
}
