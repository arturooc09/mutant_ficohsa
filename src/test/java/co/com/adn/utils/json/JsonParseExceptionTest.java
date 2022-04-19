package co.com.adn.utils.json;

import co.com.adn.adapter.router.config.AppClientGetMutant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppClientGetMutant.class)
public class JsonParseExceptionTest {

    @Spy
    @InjectMocks
    JsonParseException jsonParseException;

    @Test
    public void getJsonParseExceptionTest() {
        jsonParseException = new JsonParseException(new Exception());

    }
}
