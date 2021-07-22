package br.com.zupacademy.guilherme.proposta.feign.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseCardDto {

    private String id;

    @JsonCreator
    public ResponseCardDto(@JsonProperty("id") String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }
}
