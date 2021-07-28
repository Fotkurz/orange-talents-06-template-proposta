package br.com.zupacademy.guilherme.proposta.feign.dto;

public class WalletRequestDto {

    private String email;
    private String carteira;

    public WalletRequestDto(String email, String carteira) {
        this.email = email;
        this.carteira = carteira;
    }

    public String getEmail() {
        return email;
    }

    public String getCarteira() {
        return carteira;
    }
}
