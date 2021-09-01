package br.com.zupacademy.guilherme.proposta.feign.dto;

import br.com.zupacademy.guilherme.proposta.domain.Proposal;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;

public class RequesterCheckingDto {

    private String documento;
    private String nome;
    private String idProposta;

    public RequesterCheckingDto(Proposal proposal) {
        TextEncryptor textEncryptor = Encryptors.text("chave-secreta-ninja", "123456");
        this.documento = textEncryptor.decrypt(proposal.getDocument());
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
