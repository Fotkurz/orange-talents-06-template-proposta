package br.com.zupacademy.guilherme.proposta.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.net.URI;
import java.net.URISyntaxException;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Transactional
public class ProposalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @PersistenceContext
    private EntityManager entityManager;

    private URI proposalUri;
    private String jsonCorreto;
    private String jsonIncorreto1;
    private String jsonIncorreto2;

    @Before
    public void setUp() throws URISyntaxException, JsonProcessingException {
        this.proposalUri = new URI("/proposals");

        this.jsonCorreto = "{\n" +
                "\t\"document\":\"748.212.320-22\",\n" +
                "\t\"email\":\"tolkien@gmail.com\",\n" +
                "\t\"name\":\"Tolkien\",\n" +
                "\t\"address\":\"20 Northmor Road\",\n" +
                "\t\"earnings\":15000\n" +
                "}";

        // Email fora do padrão
        this.jsonIncorreto1 = "{\n" +
                "\t\"document\":\"748.212.320-22\",\n" +
                "\t\"email\":\"egagoaekgkaegoae\",\n" +
                "\t\"name\":\"Tolkien\",\n" +
                "\t\"address\":\"20 Northmor Road\",\n" +
                "\t\"earnings\":15000\n" +
                "}";

        // Cpf inválido
        this.jsonIncorreto2 = "{\n" +
                "\t\"document\":\"123.456.789-04\",\n" +
                "\t\"email\":\"tolkien@gmail.com\",\n" +
                "\t\"name\":\"Tolkien\",\n" +
                "\t\"address\":\"20 Northmor Road\",\n" +
                "\t\"earnings\":15000\n" +
                "}";
    }

    @Test
    @DisplayName("deveRetornar201ComCabecalhoLocationCasoPropostaTenhaSidoCriadaCorretamente")
    public void test01() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post(proposalUri)
                .contentType(MediaType.APPLICATION_JSON).content(jsonCorreto))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is(201))
                .andExpect(MockMvcResultMatchers.header().exists("Location"));
    }

    @Test
    @DisplayName("deveRetornar400CasoAlgumaValidacaoTenhaFalhado")
    public void test02() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post(proposalUri)
                .contentType(MediaType.APPLICATION_JSON).content(jsonIncorreto1))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    @DisplayName("deveRetornar400CasoCpfNaoSejaValido")
    public void test03() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post(proposalUri)
                .contentType(MediaType.APPLICATION_JSON).content(jsonIncorreto2))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is(400));
    }

}
