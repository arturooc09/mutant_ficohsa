package co.com.adn.adapter.router.error.handler;

import co.com.adn.adapter.error.ExceptionBuilder;
import co.com.adn.adapter.router.config.AppClientGetMutant;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.springframework.http.HttpStatus.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppClientGetMutant.class)
public class ErrorHandlerTest {

    private ErrorHandler errorHandler;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void init() {
        errorHandler = new ErrorHandler(objectMapper);
    }

    @Test
    public void handleRequestExceptionTest() {
        Exception ex = new Exception("Bad request error");
        ResponseEntity<Object> response = errorHandler.handleRequestException(ex);
        assertEquals(response.getStatusCode(), BAD_REQUEST);
    }

    @Test
    public void noHandlerFoundExceptionTest() {
        Exception ex = new Exception("Not found error");
        ResponseEntity<Object> response = errorHandler.noHandlerFoundException(ex);
        assertEquals(response.getStatusCode(), NOT_FOUND);
    }

    @Test
    public void handleExceptionBuilderTest() {
        ExceptionBuilder ex = new ExceptionBuilder();
        ResponseEntity<ErrorResponse> response = errorHandler.handleExceptionBuilder(ex);
        assertEquals(response.getStatusCode(), UNPROCESSABLE_ENTITY);
    }

    @Test
    public void handleGeneralErrorTest() {
        Exception ex = new Exception("Internal server error");
        ResponseEntity<ErrorResponse> response = errorHandler.handleException(ex);
        assertEquals(response.getStatusCode(), INTERNAL_SERVER_ERROR);
    }
}
