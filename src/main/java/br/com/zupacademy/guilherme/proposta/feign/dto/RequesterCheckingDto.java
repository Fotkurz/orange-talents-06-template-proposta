package br.com.zupacademy.guilherme.proposta.feign.dto;

import br.com.zupacademy.guilherme.proposta.domain.Proposal;

public class RequesterCheckingDto {

    private String documento;
    private String nome;
    private String idProposta;

    public RequesterCheckingDto(Proposal proposal) {
        this.documento = proposal.getDocument();
        this.nome = proposal.getName();
        this.idProposta = proposal.getUUID();
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public String getIdProposta() {
        return idProposta;
    }
}
