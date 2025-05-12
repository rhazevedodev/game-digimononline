package br.com.digimon.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "premium")
public class PremiumEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_digimon", nullable = false)
    private Long idDigimon;

    @Column(name = "total_dias", nullable = false)
    private int totalDias;

    @Column(name = "data_inicio", nullable = false)
    private LocalDateTime dataInicio;

    @Column(name = "data_fim", nullable = false)
    private LocalDateTime dataFim;

    public PremiumEntity() {
    }

    public PremiumEntity(Long id, Long idDigimon, int totalDias, LocalDateTime dataInicio, LocalDateTime dataFim) {
        this.id = id;
        this.idDigimon = idDigimon;
        this.totalDias = totalDias;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdDigimon() {
        return idDigimon;
    }

    public void setIdDigimon(Long idDigimon) {
        this.idDigimon = idDigimon;
    }

    public int getTotalDias() {
        return totalDias;
    }

    public void setTotalDias(int totalDias) {
        this.totalDias = totalDias;
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDateTime getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDateTime dataFim) {
        this.dataFim = dataFim;
    }
}
