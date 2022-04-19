package co.com.adn.domain.adnPerson.service;

import co.com.adn.adapter.error.ExceptionBuilder;
import co.com.adn.adapter.router.config.AppClientGetMutant;
import co.com.adn.domain.adnPerson.repository.AdnBdRepository;
import co.com.adn.domain.adnPerson.service.model.Request.Adn;
import co.com.adn.domain.adnPerson.service.model.Response.AdnStatic;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppClientGetMutant.class)
public class MutantGetServiceTest {


    @Spy
    @InjectMocks
    private MutantGetService mutantGetService = new MutantGetService();

    @Spy
    AdnBdRepository adnBdRepository;

    @InjectMocks
    AdnStatic adnStatic;

     @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void testIsMutantSuccess0() throws Exception {
         Map<String, String> headers = new HashMap<>();
         Adn adnPerson= new Adn();
         String [] dnaString = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
        adnPerson.setDna(dnaString);

        Boolean result = mutantGetService.isMutant(headers,adnPerson,"127.0.0.1");
        when(mutantGetService.isMutant(headers,adnPerson,"127.0.0.1"))
                .thenReturn(true);
        assertTrue(result);

    }

    @Test
    public void testIsMutantSuccess1() throws Exception {

        final Map<String, String> headers = new HashMap<>();
        final Adn adnPerson= new Adn();
        final String [] adnString = {"ATGCGA","CAGTGC","TTCTGT","AGAACG","CTCCTA","TCACTG"};
        adnPerson.setDna(adnString);
        Boolean result = mutantGetService.isMutant(headers,adnPerson,"127.0.0.1");
        when(mutantGetService.isMutant(headers,adnPerson,"127.0.0.1"))
                .thenReturn(Boolean.FALSE);

        assertFalse(result);
    }

    @Test
    public void testgetStatsSuccess0() throws Exception {
        Map<String, String> headers = new HashMap<>();
        List<Double> result = new ArrayList<>();
        result.add(1.0);
        result.add(2.0);
        when(adnBdRepository.getAdn())
                .thenReturn(result);

    }

}
