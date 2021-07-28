package br.com.zupacademy.guilherme.proposta.controller;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class WalletResponseDto {

    @NotBlank
    private String id;

    @JsonCreator
    public WalletResponseDto(@JsonProperty("id") String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
