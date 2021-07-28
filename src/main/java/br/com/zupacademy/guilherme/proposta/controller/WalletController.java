package br.com.zupacademy.guilherme.proposta.controller;

import br.com.zupacademy.guilherme.proposta.controller.dto.request.CardSaveRequest;
import br.com.zupacademy.guilherme.proposta.domain.DigitalWallet;
import br.com.zupacademy.guilherme.proposta.domain.Proposal;
import br.com.zupacademy.guilherme.proposta.domain.WalletType;
import br.com.zupacademy.guilherme.proposta.exception.ApiErrorException;
import br.com.zupacademy.guilherme.proposta.feign.CartaoFeignClient;
import br.com.zupacademy.guilherme.proposta.feign.dto.WalletRequestDto;
import br.com.zupacademy.guilherme.proposta.validation.ExistId;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@Validated
@RequestMapping("/api/wallet")
public class WalletController {

    @Autowired
    private DigitalWalletRepository digitalWalletRepository;

    @Autowired
    private CartaoFeignClient cartaoFeignClient;

    @PostMapping("/{cardId}")
    @Transactional
    public ResponseEntity<?> saveCardToWallet(@PathVariable("cardId") @ExistId(fieldName = "cardId", clazz = Proposal.class) String cardId,
                                              @RequestBody @Valid CardSaveRequest cardSaveRequest,
                                              UriComponentsBuilder uriComponentsBuilder) {
        try {
            Assert.isTrue(WalletType.isField(cardSaveRequest.getWalletType()), "'" + cardSaveRequest.getWalletType() + "'" + " is not a valid wallet");
        } catch (IllegalArgumentException e) {
            throw new ApiErrorException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

        Optional<DigitalWallet> cardWalletOptional = Optional.ofNullable(digitalWalletRepository.findByCardIdAndWalletType(cardId, WalletType.valueOf(cardSaveRequest.getWalletType())));
        if(cardWalletOptional.isEmpty()) {
            DigitalWallet digitalWallet = cardSaveRequest.toModel(cardId);
            WalletRequestDto walletRequestDto = cardSaveRequest.toRequestDto();
            try{
                WalletResponseDto walletResponseDto = cartaoFeignClient.addToWallet(cardId, walletRequestDto);
                digitalWallet.extractIdFromDto(walletResponseDto);
                digitalWalletRepository.save(digitalWallet);
            } catch(FeignException.BadRequest | FeignException.InternalServerError e) {}
            URI uri = uriComponentsBuilder.path("/{walletId}").buildAndExpand(digitalWallet.getId()).toUri();
            return ResponseEntity.created(uri).build();
        }
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
    }
}
