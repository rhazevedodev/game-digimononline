package br.com.digimon.domain.fromJson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaJson {
    private int id;
    private String nomeCategoria;
}
