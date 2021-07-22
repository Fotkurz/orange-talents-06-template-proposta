package br.com.zupacademy.guilherme.proposta.feign.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseCheckerDto {

    private String documento;
    private String nome;
    private String idProposta;

    @JsonProperty(value = "id")
    private String idCartao;

    @JsonCreator
    public ResponseCheckerDto(@JsonProperty("documento") String documento,
                              @JsonProperty("nome") String nome,
                              @JsonProperty("idProposta") String idProposta){
        this.documento = documento;
        this.nome = nome;
        this.idProposta = idProposta;
    }

    public boolean equals(RequesterCheckingDto requesterCheckingDto) {
        return this.documento.equals(requesterCheckingDto.getDocumento()) || this.documento.equals(requesterCheckingDto.getNome()) || this.documento.equals(requesterCheckingDto.getIdProposta());
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

    public String getIdCartao() { return idCartao; }

    public void setIdCartao(String idCartao) {
        this.idCartao = idCartao;
    }
}
