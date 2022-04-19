package co.com.adn.adapter.router.error.handler;

import co.com.adn.adapter.error.ExceptionBuilder;
import co.com.adn.utils.json.JsonUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import static co.com.adn.adapter.router.error.ErrorConsts.*;
import static org.springframework.http.HttpStatus.*;

@Slf4j
@ControllerAdvice
@NoArgsConstructor
public class ErrorHandler {

    private ObjectMapper objectMapper;

    @Autowired
    public ErrorHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @ExceptionHandler(value = {MissingServletRequestParameterException.class, MissingRequestHeaderException.class})
    public ResponseEntity<Object> handleRequestException(Exception ex) {
        ErrorResponse response = new ErrorResponse();
        MensagemResponse messagem = new MensagemResponse();

        response.setCodigo(DEFAULT_CODE_ERROR);
        messagem.setCode(DEFAULT_CODE_BAD_REQUEST);
        messagem.setMessage(BAD_REQUEST.getReasonPhrase());
        messagem.setErrorMessage(ex.getMessage());

        response.setMensagem(JsonUtils.mapJsonToString(objectMapper, messagem));

        // ex.printStackTrace();
        log.error(ex.toString());
        log.error(response.toString());

        return new ResponseEntity<>(response, BAD_REQUEST);
    }

    @ExceptionHandler(value = NoHandlerFoundException.class)
    public ResponseEntity<Object> noHandlerFoundException(Exception ex) {
        ErrorResponse response = new ErrorResponse();
        MensagemResponse messagem = new MensagemResponse();

        response.setCodigo(DEFAULT_CODE_ERROR);
        messagem.setCode(DEFAULT_CODE_NOT_FOUND);
        messagem.setMessage(NOT_FOUND.getReasonPhrase());
        messagem.setErrorMessage(ex.getMessage());

        response.setMensagem(JsonUtils.mapJsonToString(objectMapper, messagem));

        // ex.printStackTrace();
        log.error(ex.toString());
        log.error(response.toString());

        return new ResponseEntity<>(response, NOT_FOUND);
    }

    @ExceptionHandler(value = ExceptionBuilder.class)
    public ResponseEntity<ErrorResponse> handleExceptionBuilder(ExceptionBuilder ex) {
        ErrorResponse response = new ErrorResponse();
        MensagemResponse messagem = new MensagemResponse();

        response.setCodigo(DEFAULT_CODE_ERROR);
        messagem.setCode(ex.getCode());
        messagem.setMessage(ex.getMessage());

        response.setMensagem(JsonUtils.mapJsonToString(objectMapper, messagem));

        // ex.printStackTrace();
        log.error(ex.getMessage());
        log.error(response.toString());

        return new ResponseEntity<>(response, UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        ErrorResponse response = new ErrorResponse(DEFAULT_CODE_ERROR, INTERNAL_SERVER_ERROR.getReasonPhrase());

        // ex.printStackTrace();
        log.error(ex.toString());
        log.error(response.toString());

        return new ResponseEntity<>(response, INTERNAL_SERVER_ERROR);
    }
}
