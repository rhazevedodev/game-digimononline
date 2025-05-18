package br.com.digimon.domain.dto;

public class RequestCarregarTelaCacadasDTO {

    Long idDigimon;

    public RequestCarregarTelaCacadasDTO() {
    }

    public RequestCarregarTelaCacadasDTO(Long idDigimon) {
        this.idDigimon = idDigimon;
    }

    public Long getIdDigimon() {
        return idDigimon;
    }

    public void setIdDigimon(Long idDigimon) {
        this.idDigimon = idDigimon;
    }
}
