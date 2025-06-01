package br.com.digimon.domain.fromJson.itens;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaFragmentos {
    private List<FragmentoEvolucao> fragmentos;
}
