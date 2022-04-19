package co.com.adn.utils.json;


import co.com.adn.adapter.router.config.AppClientGetMutant;
import co.com.adn.domain.adnPerson.service.model.Request.Adn;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppClientGetMutant.class)
public class JsonUtilsTest {

    JsonUtils jsonUtils = new JsonUtils();

    @Test
    public void mapJsonToStringTest() {

        Object object = "ATGCGA";
        String result = "ATGCGA";
        Assert.assertEquals(jsonUtils.mapJsonToString(new ObjectMapper(),object), "\"ATGCGA\"");

    }
}
