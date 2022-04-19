package co.com.adn.domain.adnPerson.service.Model.Response;


import co.com.adn.adapter.router.config.AppClientGetMutant;
import co.com.adn.domain.adnPerson.service.model.Response.AdnStatic;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppClientGetMutant.class)
public class AdnStaticTest {

    @Test
    public void testSetAdnStaticTest(){
        AdnStatic adnStatic = new AdnStatic();
        Double countHuman = 100.0;
        Double countMutant = 40.0;
        Double ratio = 0.4;

        adnStatic.setRatio(ratio);
        adnStatic.setCount_human_dna(countHuman);
        adnStatic.setCount_mutant_dna(countMutant);

    }

    @Test
    public void testGetAdnStaticTest(){
        AdnStatic adnStatic = new AdnStatic();
        Double countHuman = 100.0;
        Double countMutant = 40.0;
        Double ratio = 0.4;

        adnStatic.setRatio(ratio);
        adnStatic.setCount_human_dna(countHuman);
        adnStatic.setCount_mutant_dna(countMutant);

        Double result = adnStatic.getCount_human_dna();
        Double result1 =adnStatic.getCount_mutant_dna();
        Double result2 = adnStatic.getRatio();

        Assert.assertEquals(countHuman,result);
        Assert.assertEquals(countMutant,result1);
        Assert.assertEquals(ratio,result2);

    }
}
