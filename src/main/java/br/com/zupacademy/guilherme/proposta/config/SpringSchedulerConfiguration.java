package br.com.zupacademy.guilherme.proposta.config;

import br.com.zupacademy.guilherme.proposta.feign.CartaoFeignClient;
import br.com.zupacademy.guilherme.proposta.repository.ProposalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@EnableAsync
public class SpringSchedulerConfiguration {

    @Autowired
    private ProposalRepository proposalRepository;
    @Autowired
    private CartaoFeignClient clientCartao;

    @Scheduled(fixedDelay = 10000)
    public void cardChecking() {
        CardChecker cardChecker = new CardChecker(proposalRepository, clientCartao);
        cardChecker.check();
    }
}
