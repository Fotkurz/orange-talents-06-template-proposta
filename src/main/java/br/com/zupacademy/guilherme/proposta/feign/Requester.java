package br.com.zupacademy.guilherme.proposta.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "solicitacoes", url = "${openfeign.client.solicitacoes}")
public interface Requester {

    @RequestMapping(method = RequestMethod.POST, value = "/api/solicitacao", consumes = "application/json")
    ResponseCheckerDto request(@RequestBody RequesterCheckingDto requesterDto);

}
