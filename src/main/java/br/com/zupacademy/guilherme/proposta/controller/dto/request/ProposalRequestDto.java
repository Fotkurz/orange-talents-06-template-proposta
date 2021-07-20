package br.com.zupacademy.guilherme.proposta.controller.dto.request;

import br.com.zupacademy.guilherme.proposta.domain.Proposal;
import br.com.zupacademy.guilherme.proposta.validation.ValidDocument;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class ProposalRequestDto {

    @NotBlank @ValidDocument
    private String document;
    @NotBlank @Email
    private String email;
    @NotBlank
    private String name;
    @NotBlank
    private String address;
    @NotNull
    @Positive
    private BigDecimal earnings;

    @JsonCreator
    public ProposalRequestDto(@JsonProperty("document") String document,
                              @JsonProperty("email") String email,
                              @JsonProperty("name") String name,
                              @JsonProperty("address") String address,
                              @JsonProperty("earnings") BigDecimal earnings) {
        this.document = document;
        this.email = email;
        this.name = name;
        this.address = address;
        this.earnings = earnings;
    }


    public Proposal toModel() {
        return new Proposal(document, email, name, address, earnings);
    }
}
