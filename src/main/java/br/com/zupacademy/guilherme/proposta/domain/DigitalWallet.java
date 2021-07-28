package br.com.zupacademy.guilherme.proposta.domain;

import br.com.zupacademy.guilherme.proposta.controller.WalletResponseDto;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "tbl_digitalwallet")
public class DigitalWallet {

    @Id
    private String uuid;
    @NotBlank @Email
    private String email;
    @NotBlank
    private String cardId;
    @Enumerated(EnumType.STRING)
    private WalletType walletType;

    @Deprecated
    public DigitalWallet(){}

    public DigitalWallet(String email, String cardId, String walletType) {
        this.email = email;
        this.cardId = cardId;
        this.walletType = WalletType.valueOf(walletType);
    }

    public void extractIdFromDto(WalletResponseDto walletResponseDto) {
        this.uuid = walletResponseDto.getId();
    }

    public String getId() {
        return this.uuid;
    }
}
