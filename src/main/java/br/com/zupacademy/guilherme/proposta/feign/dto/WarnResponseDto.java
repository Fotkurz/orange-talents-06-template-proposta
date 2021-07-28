package br.com.zupacademy.guilherme.proposta.feign.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WarnResponseDto {

    private String resultado;

    @JsonCreator
    public WarnResponseDto(@JsonProperty("resultado") String resultado) {
        this.resultado = resultado;
    }
    public boolean checkResponse() {
        return this.resultado.equals("CRIADO");
    }
}
