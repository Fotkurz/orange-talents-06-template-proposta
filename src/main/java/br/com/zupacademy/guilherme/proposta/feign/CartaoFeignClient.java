package br.com.zupacademy.guilherme.proposta.feign;

import br.com.zupacademy.guilherme.proposta.controller.dto.response.BlockResponseDto;
import br.com.zupacademy.guilherme.proposta.feign.dto.ResponseCardDto;
import br.com.zupacademy.guilherme.proposta.feign.dto.WarnRequestDto;
import br.com.zupacademy.guilherme.proposta.feign.dto.WarnResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(value = "cartoes", url = "${openfeign.client.cartoes}")
public interface CartaoFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/api/cartoes", consumes = "application/json")
    ResponseCardDto request(@RequestParam(name="idProposta", defaultValue = "") String idProposta);

    @RequestMapping(method = RequestMethod.POST, value ="/api/cartoes/{id}/bloqueios", consumes = "application/json")
    BlockResponseDto block(@PathVariable(name="id") String idCard, @RequestBody Map<String, String> aplicacaoResponsavel);

    @RequestMapping(method = RequestMethod.POST, value ="/api/cartoes/{id}/avisos", consumes = "application/json")
    WarnResponseDto warn(@PathVariable(name="id") String idCard, @RequestBody WarnRequestDto warnRequestDto);
}
