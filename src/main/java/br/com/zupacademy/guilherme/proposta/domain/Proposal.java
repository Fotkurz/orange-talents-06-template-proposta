package br.com.zupacademy.guilherme.proposta.domain;

import br.com.zupacademy.guilherme.proposta.validation.ValidDocument;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "tbl_proposals")
public class Proposal {

    @Id
    private String uuid = UUID.randomUUID().toString();
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

    private String cardId;

    @Enumerated(EnumType.STRING)
    private Legible legible;


    @Deprecated
    public Proposal() { }

    public Proposal(String document, String email, String name, String address, BigDecimal earnings) {
        this.document = document;
        this.email = email;
        this.name = name;
        this.address = address;
        this.earnings = earnings;
    }

    public String getUUID() {
        return this.uuid;
    }

    public String getDocument() {
        return document;
    }

    public String getName() {
        return name;
    }

    public void isLegible(Boolean respostaSolicitacao) {
        if(respostaSolicitacao) this.legible = Legible.ELEGIVEL;
        else this.legible = Legible.NAO_ELEGIVEL;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

}
