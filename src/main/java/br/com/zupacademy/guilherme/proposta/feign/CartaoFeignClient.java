package br.com.zupacademy.guilherme.proposta.feign;

import br.com.zupacademy.guilherme.proposta.feign.dto.ResponseCardDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "cartoes", url = "${openfeign.client.cartoes}")
public interface CartaoFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/api/cartoes", consumes = "application/json")
    ResponseCardDto request(@RequestParam(name="idProposta", defaultValue = "") String idProposta);

}
