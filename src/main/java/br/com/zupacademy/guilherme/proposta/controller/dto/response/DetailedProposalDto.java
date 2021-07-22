package br.com.zupacademy.guilherme.proposta.controller.dto.response;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;

public class DetailedProposalDto {

    private String uuid;
    private String document;
    private String email;
    private String name;
    private String address;
    private BigDecimal earnings;
    private String cardId;

    @Enumerated(EnumType.STRING)
    private String legible;

    public DetailedProposalDto(String uuid, String document, String email, String name, String address, BigDecimal earnings, String legible, String cardId) {
        this.uuid = uuid;
        this.document = document;
        this.email = email;
        this.name = name;
        this.address = address;
        this.earnings = earnings;
        this.cardId = cardId;
        this.legible = legible;
    }


    public String getUuid() {
        return uuid;
    }

    public String getDocument() {
        return document;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public BigDecimal getEarnings() {
        return earnings;
    }

    public String getCardId() {
        return cardId;
    }

    public String getLegible() {
        return legible;
    }
}
