package co.com.adn.adapter.router.error;

import co.com.adn.adapter.router.config.AppClientGetMutant;
import co.com.adn.adapter.router.RouterConsts;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppClientGetMutant.class)
public class ErrorConstsTest {

    private ErrorConsts errorConsts;
    private RouterConsts errorConstsRouter;


    @Before
    public void init() {

        errorConsts = new ErrorConsts();
        errorConstsRouter = new RouterConsts();
    }


    @Test
    public void errorTest() {
      assertEquals(errorConsts.DEFAULT_CODE_BAD_REQUEST, "E0400");
        assertEquals(errorConsts.DEFAULT_CODE_NOT_FOUND, "E0404");
        assertEquals(errorConsts.DEFAULT_CODE_ERROR, "");

    }


    @Test
    public void errorTestRouter() {

        assertEquals(errorConstsRouter.INITIAL_PATH, "/");
        assertEquals(errorConstsRouter.CSRF_PATH, "/csrf");
        assertEquals(errorConstsRouter.CONTROLLER_PATH, "/mutant/v1");
        assertEquals(errorConstsRouter.MUTANT_PATH, "/mutant");

    }

}
