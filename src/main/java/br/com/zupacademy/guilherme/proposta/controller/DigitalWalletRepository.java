package br.com.zupacademy.guilherme.proposta.controller;

import br.com.zupacademy.guilherme.proposta.domain.DigitalWallet;
import br.com.zupacademy.guilherme.proposta.domain.WalletType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DigitalWalletRepository extends JpaRepository<DigitalWallet, String> {
    DigitalWallet findByCardIdAndWalletType(String cardId, WalletType walletType);
}
