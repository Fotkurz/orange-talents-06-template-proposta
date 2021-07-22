package br.com.zupacademy.guilherme.proposta.repository;

import br.com.zupacademy.guilherme.proposta.config.CardChecker;
import br.com.zupacademy.guilherme.proposta.domain.Proposal;
import br.com.zupacademy.guilherme.proposta.feign.SolicitacaoFeignClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Transactional
public class CardCheckTest {

    @Autowired
    ProposalRepository proposalRepository;

    @Autowired
    SolicitacaoFeignClient clientSolicitacao;

    Proposal proposal1;
    Proposal proposal2;
    Proposal proposal3;
    Proposal proposal4;

    @Autowired
    CardChecker cardChecker;

    @Before
    public void setUp() {
        System.out.println("Populando banco");
        proposal1 = new Proposal("574.745.510-98",
                "tolkien@gmail.com",
                "Tolkien",
                "20 Northmor Road",
                BigDecimal.valueOf(14000));
        proposal1.isLegible(true);

        proposal2 = new Proposal("333.183.600-15",
                "lovecraft@gmail.com",
                "Lovecraft",
                "20 Northmor Road",
                BigDecimal.valueOf(14000));
        proposal2.isLegible(false);

        proposal3 = new Proposal("574.745.510-98",
                "Asimov@gmail.com",
                "Asimov",
                "20 Northmor Road",
                BigDecimal.valueOf(14000));
        proposal3.isLegible(true);

        proposal4 = new Proposal("574.745.510-98",
                "Wells@gmail.com",
                "Wells",
                "20 Northmor Road",
                BigDecimal.valueOf(14000));
        proposal4.isLegible(true);

        proposalRepository.save(proposal1);
        proposalRepository.save(proposal2);
        proposalRepository.save(proposal3);
        proposalRepository.save(proposal4);


    }

    @Test
    @DisplayName("deveImprimirAListaAoChamarACardCheckerCheck")
    public void test01 () {
        cardChecker.check();

    }

}
