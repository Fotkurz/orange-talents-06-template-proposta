package br.com.zupacademy.guilherme.proposta.controller;

import br.com.zupacademy.guilherme.proposta.controller.dto.request.BlockRequestDto;
import br.com.zupacademy.guilherme.proposta.controller.dto.response.BlockResponseDto;
import br.com.zupacademy.guilherme.proposta.domain.BlockCard;
import br.com.zupacademy.guilherme.proposta.domain.Proposal;
import br.com.zupacademy.guilherme.proposta.feign.CartaoFeignClient;
import br.com.zupacademy.guilherme.proposta.repository.BlockCardRepository;
import br.com.zupacademy.guilherme.proposta.repository.ProposalRepository;
import br.com.zupacademy.guilherme.proposta.validation.ExistId;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@Validated
public class CardController {

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
            Map<String, String> map = new HashMap<>();
            map.put("aplicacaoResponsavel", "Proposal");
            try{
                BlockResponseDto blockResponseDto = cartaoFeignClient.block(cardId, map);
                if(blockResponseDto.isBlocked()) blockCard.block();
                blockCardRepository.save(blockCard);
            }catch (FeignException.BadRequest | FeignException.InternalServerError e){
            }
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
    }

}
