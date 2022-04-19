package co.com.adn.adapter.router.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;

import static co.com.adn.adapter.router.RouterConsts.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@ApiIgnore
public class SwaggerController {

    @RequestMapping(value = INITIAL_PATH, method = GET, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> getUrl() {
        return new ResponseEntity<>(DEFAULT_VALUE, HttpStatus.OK);
    }

    @RequestMapping(value = CSRF_PATH, method = GET, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<CsrfToken> getToken(final HttpServletRequest request) {
        return ResponseEntity.ok().body(new HttpSessionCsrfTokenRepository().generateToken(request));
    }
}
