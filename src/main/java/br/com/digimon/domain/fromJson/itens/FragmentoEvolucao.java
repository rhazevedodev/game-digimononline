package br.com.digimon.domain.fromJson.itens;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FragmentoEvolucao {
    private int id;
    private int idCategoria;
    private String nome;
    private String descricao;
    @JsonProperty("valor_compra")
    private int valorCompra;
    @JsonProperty("valor_venda")
    private int valorVenda;
    @JsonProperty("pode_vender")
    private boolean podeVender;
    @JsonProperty("pode_trocar")
    private boolean podeTrocar;
    @JsonProperty("is_global")
    private boolean isGlobal;
}
