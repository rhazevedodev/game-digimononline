package br.com.digimon.domain.fromJson.itens;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItensWrapper {
    private List<Itens> itens;
}
