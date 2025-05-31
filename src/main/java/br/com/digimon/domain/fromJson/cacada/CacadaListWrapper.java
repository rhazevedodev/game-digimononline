package br.com.digimon.domain.fromJson.cacada;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CacadaListWrapper {
    private List<Cacada> cacadas;
}
