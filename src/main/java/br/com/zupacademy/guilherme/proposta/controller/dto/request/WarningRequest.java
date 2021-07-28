package br.com.zupacademy.guilherme.proposta.controller.dto.request;

import br.com.zupacademy.guilherme.proposta.domain.TripWarning;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class WarningRequest {

    @NotBlank
    private String tripDestiny;
    @NotNull @Future
    private LocalDate tripEndingDate;

    public WarningRequest(String tripDestiny, LocalDate tripEndingDate) {
        this.tripDestiny = tripDestiny;
        this.tripEndingDate = tripEndingDate;
    }

    public TripWarning toModel(String validCardId, String clientRemoteAddress, String userAgent){
        return new TripWarning(validCardId, tripDestiny, tripEndingDate, clientRemoteAddress, userAgent);
    }

}
