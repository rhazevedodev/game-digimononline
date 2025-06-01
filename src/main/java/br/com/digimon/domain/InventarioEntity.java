package br.com.digimon.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "inventario")
public class InventarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idDigimon;

    private int idItem;

    private String nomeItem;

    private String descricaoItem;

    private int quantidade;

    private int idCategoria;

    private int valorCompra;

    private int valorVenda;

//    private boolean isFragmento;

//    private boolean isConsumivel;

//    private boolean isEquipamento;

    private boolean equipado;

    private boolean podeVender;

    private boolean podeTrocar;

    private LocalDateTime dataUltimaAlteracao;

    private boolean isGlobal;
}
