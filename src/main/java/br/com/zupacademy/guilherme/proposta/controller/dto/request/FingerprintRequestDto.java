package br.com.zupacademy.guilherme.proposta.controller.dto.request;


import br.com.zupacademy.guilherme.proposta.domain.ClientFingerprint;
import br.com.zupacademy.guilherme.proposta.domain.Proposal;
import br.com.zupacademy.guilherme.proposta.validation.isBase64;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class FingerprintRequestDto {

    @NotBlank @isBase64
    private String fingerprint;

    @JsonCreator
    public FingerprintRequestDto(@JsonProperty("fingerprint") String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public ClientFingerprint toModel(Proposal proposal) {
        byte[] encoded = Base64.getDecoder().decode(fingerprint.getBytes(StandardCharsets.UTF_8));
        String fingerPrint = new String(encoded);
        return new ClientFingerprint(fingerPrint, proposal);
    }
}
