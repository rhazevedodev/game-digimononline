package br.com.digimon.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "cacada")
public class CacadaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idDigimon;

    private int idCacada;

    private LocalDate data = LocalDate.now();

    private int segundos;

    private LocalDateTime horaResgateDisponivel;

    private boolean recompensaResgatada;

    private LocalDateTime ultimaAlteracao = LocalDateTime.now();


    public CacadaEntity(Long idDigimon, int segundos, LocalDateTime horaResgateDisponivel, boolean recompensaResgatada) {
        this.idDigimon = idDigimon;
        this.segundos = segundos;
        this.horaResgateDisponivel = horaResgateDisponivel;
        this.recompensaResgatada = recompensaResgatada;
    }

    public CacadaEntity(Long idDigimon, int segundos, LocalDateTime horaResgateDisponivel, boolean recompensaResgatada, LocalDateTime ultimaAlteracao) {
        this.idDigimon = idDigimon;
        this.segundos = segundos;
        this.horaResgateDisponivel = horaResgateDisponivel;
        this.recompensaResgatada = recompensaResgatada;
        this.ultimaAlteracao = ultimaAlteracao;
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

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public int getSegundos() {
        return segundos;
    }

    public void setSegundos(int segundos) {
        this.segundos = segundos;
    }

    public LocalDateTime getHoraResgateDisponivel() {
        return horaResgateDisponivel;
    }

    public void setHoraResgateDisponivel(LocalDateTime horaResgateDisponivel) {
        this.horaResgateDisponivel = horaResgateDisponivel;
    }

    public boolean isRecompensaResgatada() {
        return recompensaResgatada;
    }

    public void setRecompensaResgatada(boolean recompensaResgatada) {
        this.recompensaResgatada = recompensaResgatada;
    }

    public LocalDateTime getUltimaAlteracao() {
        return ultimaAlteracao;
    }

    public void setUltimaAlteracao(LocalDateTime ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }
}
