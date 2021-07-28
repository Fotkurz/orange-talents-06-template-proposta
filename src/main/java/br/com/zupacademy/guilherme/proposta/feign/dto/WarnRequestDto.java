package br.com.zupacademy.guilherme.proposta.feign.dto;

import java.time.LocalDate;

public class WarnRequestDto {

    private String destino;
    private LocalDate validoAte;

    public WarnRequestDto(String tripDestiny, LocalDate tripEndingDate) {
        this.destino = tripDestiny;
        this.validoAte = tripEndingDate;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }
}
