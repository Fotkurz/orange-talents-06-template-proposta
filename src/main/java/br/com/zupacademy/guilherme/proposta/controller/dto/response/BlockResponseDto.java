package br.com.zupacademy.guilherme.proposta.controller.dto.response;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BlockResponseDto {

    private String resultado;

    @JsonCreator
    public BlockResponseDto(@JsonProperty("resultado") String resultado) {
        this.resultado = resultado;
    }

    public String getResultado() {
        return resultado;
    }

    public boolean isBlocked() {
        return this.resultado.equals("BLOQUEADO");
    }
}
