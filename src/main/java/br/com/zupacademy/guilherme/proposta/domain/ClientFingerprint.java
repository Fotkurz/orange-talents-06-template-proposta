package br.com.zupacademy.guilherme.proposta.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "tbl_fingerprints")
public class ClientFingerprint {

    @Id
    private String id = UUID.randomUUID().toString();

    @NotNull
    @NotBlank
    private String fingerPrint;

    @FutureOrPresent
    private LocalDate registerDate = LocalDate.now();

    @ManyToOne
    private Proposal proposal;

    public ClientFingerprint(String fingerPrint, Proposal proposal) {
        this.fingerPrint = fingerPrint;
        this.proposal = proposal;
    }

    public String getUuid() {
        return this.id.toString();
    }
}
