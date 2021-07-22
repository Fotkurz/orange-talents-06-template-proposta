package br.com.zupacademy.guilherme.proposta.controller.dto.response;

import java.util.Collection;

public class FormErrorDto {

    private Collection<String> messages;

    public FormErrorDto(Collection<String> messages) {
        this.messages = messages;
    }

    public FormErrorDto() {

    }

    public Collection<String> getMessage() {
        return messages;
    }
}
