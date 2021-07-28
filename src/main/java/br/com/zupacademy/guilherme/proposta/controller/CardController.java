package br.com.zupacademy.guilherme.proposta.controller;

import br.com.zupacademy.guilherme.proposta.controller.dto.request.BlockRequestDto;
import br.com.zupacademy.guilherme.proposta.controller.dto.request.WarningRequest;
import br.com.zupacademy.guilherme.proposta.controller.dto.response.BlockResponseDto;
import br.com.zupacademy.guilherme.proposta.domain.BlockCard;
import br.com.zupacademy.guilherme.proposta.domain.Proposal;
import br.com.zupacademy.guilherme.proposta.domain.TripWarning;
import br.com.zupacademy.guilherme.proposta.feign.CartaoFeignClient;
import br.com.zupacademy.guilherme.proposta.feign.dto.WarnResponseDto;
import br.com.zupacademy.guilherme.proposta.repository.BlockCardRepository;
import br.com.zupacademy.guilherme.proposta.repository.ProposalRepository;
import br.com.zupacademy.guilherme.proposta.validation.ExistId;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@Validated
public class CardController {

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private HttpServletRequest servletRequest;
    @Autowired
    private ProposalRepository proposalRepository;
    @Autowired
    private BlockCardRepository blockCardRepository;
    @Autowired
    private CartaoFeignClient cartaoFeignClient;

    @PostMapping("/api/cards/block/{cardId}")
    public ResponseEntity<?> block(@PathVariable("cardId")
                                       @ExistId(fieldName = "cardId", clazz = Proposal.class) String cardId,
                                   @RequestHeader(value = "User-Agent") String userAgent){
        Optional<BlockCard> blockCardOptional = Optional.ofNullable(blockCardRepository.findByProposalCardId(cardId));
        if(blockCardOptional.isEmpty()) {
            Proposal proposal = proposalRepository.findByCardId(cardId);
            BlockRequestDto blockRequestDto = new BlockRequestDto(servletRequest.getRemoteAddr(), userAgent);
            BlockCard blockCard = blockRequestDto.toModel(proposal);
            Map<String, String> jsonRequest = new HashMap<>();
            jsonRequest.put("aplicacaoResponsavel", "Proposal");
            try{
                BlockResponseDto blockResponseDto = cartaoFeignClient.block(cardId, jsonRequest);
                if(blockResponseDto.isBlocked()) blockCard.block();
                blockCardRepository.save(blockCard);
            }catch (FeignException.BadRequest | FeignException.InternalServerError e){
            }
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
    }

    @PostMapping("/api/cards/tripwarning/{cardId}")
    @Transactional
    public ResponseEntity<?> warning(@PathVariable("cardId") @ExistId(fieldName = "cardId", clazz = Proposal.class) String cardId,
                                     @RequestBody @Valid WarningRequest warningRequest,
                                     @RequestHeader("User-Agent") String userAgent){
        TripWarning tripWarning = warningRequest.toModel(cardId, servletRequest.getRemoteAddr(), userAgent);
        try {
            WarnResponseDto warnResponseDto = cartaoFeignClient.warn(cardId, tripWarning.toLegacyRequest());
            if(warnResponseDto.checkResponse()) entityManager.persist(tripWarning);
        } catch (FeignException.BadRequest | FeignException.InternalServerError e) {
        }
        return ResponseEntity.ok().build();
    }
}
