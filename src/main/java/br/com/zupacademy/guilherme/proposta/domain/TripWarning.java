package br.com.zupacademy.guilherme.proposta.domain;

import br.com.zupacademy.guilherme.proposta.feign.dto.WarnRequestDto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tbl_tripwarning")
public class TripWarning {

    @Id
    private String uuid = UUID.randomUUID().toString();
    @NotBlank
    private String cardId;
    @NotBlank
    private String tripDestiny;
    @Future @NotNull
    private LocalDate tripEndingDate;
    @NotNull
    private LocalDateTime tripWarningInstant = LocalDateTime.now();
    @NotBlank
    private String clientIp;
    @NotBlank
    private String userAgent;

    @Deprecated
    public TripWarning(){}

    public TripWarning(String cardId, String tripDestiny, LocalDate tripEndingDate, String clientIp, String userAgent) {
        this.cardId = cardId;
        this.tripDestiny = tripDestiny;
        this.tripEndingDate = tripEndingDate;
        this.clientIp = clientIp;
        this.userAgent = userAgent;
    }

    public WarnRequestDto toLegacyRequest() {
        return new WarnRequestDto(this.tripDestiny, this.tripEndingDate);
    }
}
