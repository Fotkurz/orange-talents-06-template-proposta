package br.com.zupacademy.guilherme.proposta.feign;

import br.com.zupacademy.guilherme.proposta.feign.dto.RequesterCheckingDto;
import br.com.zupacademy.guilherme.proposta.feign.dto.ResponseCheckerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "solicitacoes", url = "${openfeign.client.solicitacoes}")
public interface SolicitacaoFeignClient {

    @RequestMapping(method = RequestMethod.POST, value = "/api/solicitacao", consumes = "application/json")
    ResponseCheckerDto request(@RequestBody RequesterCheckingDto requesterDto);

}
