package br.com.zupacademy.guilherme.proposta.controller.dto.request;


import br.com.zupacademy.guilherme.proposta.domain.BlockCard;
import br.com.zupacademy.guilherme.proposta.domain.Proposal;

import javax.validation.constraints.NotBlank;

public class BlockRequestDto {

    @NotBlank
    private String userIp;
    @NotBlank
    private String userAgent;

    public BlockRequestDto(String userIp, String userAgent) {
        this.userIp = userIp;
        this.userAgent = userAgent;
    }

    public BlockCard toModel(Proposal proposal){
        return new BlockCard(proposal, this.userIp, this.userAgent);
    }
}
