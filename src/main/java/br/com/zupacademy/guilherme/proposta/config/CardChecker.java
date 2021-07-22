package br.com.zupacademy.guilherme.proposta.config;

import br.com.zupacademy.guilherme.proposta.domain.Proposal;
import br.com.zupacademy.guilherme.proposta.feign.CartaoFeignClient;
import br.com.zupacademy.guilherme.proposta.feign.dto.RequesterCheckingDto;
import br.com.zupacademy.guilherme.proposta.feign.dto.ResponseCardDto;
import br.com.zupacademy.guilherme.proposta.repository.ProposalRepository;
import feign.FeignException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class CardChecker {

    private ProposalRepository proposalRepository;

    private CartaoFeignClient clientCartao;

    CardChecker(ProposalRepository proposalRepository, CartaoFeignClient clientCartao) {
        this.proposalRepository = proposalRepository;
        this.clientCartao = clientCartao;
    }

    @Async
    @Transactional
    public void check() {
        List<Proposal> proposals = proposalRepository.findAllCardIdIsNull();
        if(!proposals.isEmpty()) {
            proposals.forEach(p -> {
                try {
                    RequesterCheckingDto requesterCheckingDto = new RequesterCheckingDto(p);
                    ResponseCardDto responseCartaoDto = clientCartao.request(requesterCheckingDto.getIdProposta());
                    p.setCardId(responseCartaoDto.getId());
                } catch (FeignException.BadRequest | FeignException.InternalServerError e) {

                }
            });
        }
        proposalRepository.saveAllAndFlush(proposals);
    }
}
