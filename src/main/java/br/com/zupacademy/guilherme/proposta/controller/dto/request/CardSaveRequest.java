package br.com.zupacademy.guilherme.proposta.controller.dto.request;

import br.com.zupacademy.guilherme.proposta.domain.DigitalWallet;
import br.com.zupacademy.guilherme.proposta.feign.dto.WalletRequestDto;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class CardSaveRequest {

    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String walletType;

    @JsonCreator
    public CardSaveRequest(@JsonProperty("email") String email,
                           @JsonProperty("walletType") String walletType) {
        this.email = email;
        this.walletType = walletType;
    }

    public DigitalWallet toModel(String cardId) {
        return new DigitalWallet(email, cardId, walletType);
    }

    public String getWalletType() {
        return walletType;
    }

    public WalletRequestDto toRequestDto() {
        return new WalletRequestDto(this.email, this.walletType);
    }
}
