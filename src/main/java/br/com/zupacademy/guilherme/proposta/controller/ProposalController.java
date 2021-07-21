package br.com.zupacademy.guilherme.proposta.controller;

import br.com.zupacademy.guilherme.proposta.controller.dto.request.ProposalRequestDto;
import br.com.zupacademy.guilherme.proposta.domain.Proposal;
import br.com.zupacademy.guilherme.proposta.exception.ApiErrorException;
import br.com.zupacademy.guilherme.proposta.feign.Requester;
import br.com.zupacademy.guilherme.proposta.feign.RequesterCheckingDto;
import br.com.zupacademy.guilherme.proposta.feign.ResponseCheckerDto;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/proposals")
public class ProposalController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private Requester requester;

    @PostMapping
    @Transactional
    public ResponseEntity<?> create(@RequestBody @Valid ProposalRequestDto proposalRequestDto,
                                    UriComponentsBuilder uriComponentsBuilder) throws ApiErrorException {
        try {
            Assert.notNull(proposalRequestDto, "Invalid Json");
            Proposal proposal = proposalRequestDto.toModel();
            URI uri = uriComponentsBuilder.path("/proposals/{uuid}").buildAndExpand(proposal.getUUID()).toUri();
            try {
                RequesterCheckingDto requesterCheckingDto = new RequesterCheckingDto(proposal);
                ResponseCheckerDto responseCheckerDto = requester.request(requesterCheckingDto);
                Assert.isTrue(responseCheckerDto.equals(requesterCheckingDto), "Dados de resposta são inválidos");
                proposal.isLegible(true);
            } catch(FeignException.UnprocessableEntity h){
                proposal.isLegible(false);
            }

            entityManager.persist(proposal);
            return ResponseEntity.created(uri).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }

    }
}
