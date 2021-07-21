package br.com.zupacademy.guilherme.proposta.feign;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseCheckerDto {

    private String documento;
    private String nome;
    private String idProposta;

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
}
