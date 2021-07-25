package br.com.zupacademy.guilherme.proposta.controller;

import br.com.zupacademy.guilherme.proposta.controller.dto.request.FingerprintRequestDto;
import br.com.zupacademy.guilherme.proposta.domain.ClientFingerprint;
import br.com.zupacademy.guilherme.proposta.domain.Proposal;
import br.com.zupacademy.guilherme.proposta.repository.ProposalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/fingerprints")
public class FingerprintController {

    @Autowired
    private ProposalRepository proposalRepository;

    @PersistenceContext
    private EntityManager em;

    @PostMapping("/{id}")
    @Transactional
    public ResponseEntity<?> create (@PathVariable("id") String idCartao, @RequestBody @Valid FingerprintRequestDto fingerprintRequestDto,
                                     UriComponentsBuilder uriComponentsBuilder){
        Optional<Proposal> optionalProposal = Optional.ofNullable(proposalRepository.findByCardId(idCartao));

        if(optionalProposal.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        ClientFingerprint fingerprint = fingerprintRequestDto.toModel(optionalProposal.get());
        em.persist(fingerprint);

        URI uri = uriComponentsBuilder.path("/api/fingerprints/{uuid}").buildAndExpand(fingerprint.getUuid()).toUri();
        return ResponseEntity.created(uri).build();
    }

}
