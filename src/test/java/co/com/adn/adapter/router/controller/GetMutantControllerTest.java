package co.com.adn.adapter.router.controller;

import co.com.adn.AbstractTest;
import co.com.adn.adapter.bd.BdConsts;
import co.com.adn.adapter.router.RouterConsts;
import co.com.adn.adapter.router.config.AppClientGetMutant;
import co.com.adn.domain.adnPerson.service.MutantGetService;
import co.com.adn.domain.adnPerson.service.model.Request.Adn;
import co.com.adn.domain.adnPerson.service.model.Response.AdnStatic;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppClientGetMutant.class)

public class GetMutantControllerTest extends AbstractTest {

    @MockBean
    private GetMutantController getMutantController;

    @InjectMocks
    @Spy
    private MutantGetService mutantGetService;

    private MockMvc mockMvc;
    private HttpHeaders headers;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void init() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        headers();
    }
    private void headers() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("session_id", "1287287287237872");
        headers.add("timestamp", "2008-09-28T20:49:45");
        headers.add("application", "Portal");
        headers.add("channel-id", "14");
    }

    @Test
    public void status200TestStats() throws Exception {

        AdnStatic adnStatic = new AdnStatic();
        adnStatic.setRatio(0.4);
        adnStatic.setCount_human_dna(100.0);
        adnStatic.setCount_mutant_dna(40.0);
        String urlStatusService = RouterConsts.CONTROLLER_PATH.concat(RouterConsts.STATS_PATH);
        mockMvc.perform(get(urlStatusService)
                .headers(headers)).andExpect(status().isOk());
    }

    @Test
    public void status200Test() throws Exception {
        final Adn adnPerson= new Adn();
        final String [] dnaString =  {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
        adnPerson.setDna(dnaString);
        BdConsts bdConsts = new BdConsts();
        String urlStatusService = RouterConsts.CONTROLLER_PATH.concat(RouterConsts.MUTANT_PATH);
        mockMvc.perform(post(urlStatusService).contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(adnPerson))
                .headers(headers)).andExpect(status().is2xxSuccessful());

    }

    @Test
    public void status403Test() throws Exception {
        String urlMutantService = RouterConsts.CONTROLLER_PATH.concat(RouterConsts.MUTANT_PATH);
        final HttpServletResponse response = new MockHttpServletResponse();
        
        response.setStatus(403);
        final Adn adnPerson= new Adn();
        final String [] dnaString = {"ATCA","CAGA","TTCC"};
        adnPerson.setDna(dnaString);

        mockMvc.perform(post(urlMutantService).contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(adnPerson))
                .headers(headers)).andReturn();


    }

 }
