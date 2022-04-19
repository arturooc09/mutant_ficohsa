package co.com.adn.adapter.router.error.builder;

import co.com.adn.adapter.error.builder.BdExceptionBuilder;
import co.com.adn.adapter.router.config.AppClientGetMutant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppClientGetMutant.class)
public class BDExceptionBuilderTest {

    @Test
    public void SuccesConstructorTest(){
        BdExceptionBuilder bdExceptionBuilder = new BdExceptionBuilder("001","Error Connection");

    }


}
