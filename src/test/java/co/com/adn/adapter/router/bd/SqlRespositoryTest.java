package co.com.adn.adapter.router.bd;

import co.com.adn.adapter.router.config.AppClientGetMutant;
import co.com.adn.domain.adnPerson.repository.AdnBdRepository;
import co.com.adn.domain.adnPerson.service.model.Request.Adn;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppClientGetMutant.class)
public class SqlRespositoryTest {

    @Autowired
    AdnBdRepository sqlRepository;

    @Test
    public void testRegisterAdnSuccess0() throws SQLException, GeneralSecurityException, IOException, ClassNotFoundException {
        Map<String, String> headers = new HashMap<>();
        Adn adnPerson= new Adn();
        String [] dnaString = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
        adnPerson.setDna(dnaString);
        sqlRepository.RegisterAdn(adnPerson,1);

    }

    @Test
    public void testGetSuccess0() {
      List<Double> coutAdn = sqlRepository.getAdn();
        List<Double> coutAdnExepct = new ArrayList<>();
        coutAdnExepct.add(1.0);
        coutAdnExepct.add(2.0);

      Assert.assertEquals(coutAdn,coutAdn);

    }
}
