package co.com.adn.adapter.router.controller;

import co.com.adn.adapter.error.ExceptionBuilder;
import co.com.adn.adapter.router.error.handler.ErrorResponse;
import co.com.adn.domain.adnPerson.service.MutantGetService;
import co.com.adn.domain.adnPerson.service.model.Request.Adn;
import co.com.adn.domain.adnPerson.service.model.Response.AdnStatic;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

import static co.com.adn.adapter.router.RouterConsts.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping(CONTROLLER_PATH)
@Api(tags = SWAG_API_TAGS)
public class GetMutantController {

    private MutantGetService mutantGetService;

    @Autowired
    public GetMutantController(MutantGetService clientGetMutantService) {
        this.mutantGetService = clientGetMutantService;
    }

    @PostMapping(value = MUTANT_PATH, produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = SWAG_OPERATION_VALUE, notes = SWAG_OPERATION_NOTES)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = SWAG_RESPONSE_200),
        @ApiResponse(code = 400, message = SWAG_RESPONSE_400, response = ErrorResponse.class),
        @ApiResponse(code = 403, message = SWAG_RESPONSE_404, response = ErrorResponse.class),
        @ApiResponse(code = 404, message = SWAG_RESPONSE_403, response = ErrorResponse.class),
        @ApiResponse(code = 500, message = SWAG_RESPONSE_500, response = ErrorResponse.class)
    })
    public void getMutant(
            @RequestHeader Map<String, String> headers,
            @RequestHeader(name = HEADER_SESSION_ID) String sessionId,
            @RequestHeader(name = HEADER_CHANNEL_ID) String channelId,
            @RequestHeader(name = HEADER_APPLICATION) String application,
            @RequestHeader(name = HEADER_TIMESTAMP) String timestamp,
            @RequestBody Adn requestMutant,
            HttpServletRequest request, HttpServletResponse response
    ) throws ExceptionBuilder {

        log.info("RequestBody: ".concat(requestMutant.toString()));
        log.info("Headers: ".concat(headers.toString()));

        if (!mutantGetService.isMutant(headers,requestMutant, request.getRemoteAddr())){
            response.setStatus(403);
        }
    }

    @GetMapping(value = STATS_PATH, produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = SWAG_OPERATION_VALUE, notes = SWAG_OPERATION_NOTES)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = SWAG_RESPONSE_200),
            @ApiResponse(code = 400, message = SWAG_RESPONSE_400, response = ErrorResponse.class),
            @ApiResponse(code = 403, message = SWAG_RESPONSE_404, response = ErrorResponse.class),
            @ApiResponse(code = 404, message = SWAG_RESPONSE_403, response = ErrorResponse.class),
            @ApiResponse(code = 500, message = SWAG_RESPONSE_500, response = ErrorResponse.class)
    })
    public AdnStatic getStats(
            @RequestHeader Map<String, String> headers,
            @RequestHeader(name = HEADER_SESSION_ID) String sessionId,
            @RequestHeader(name = HEADER_CHANNEL_ID) String channelId,
            @RequestHeader(name = HEADER_APPLICATION) String application,
            @RequestHeader(name = HEADER_TIMESTAMP) String timestamp
    )  {
        log.info("Headers: ".concat(headers.toString()));
       return mutantGetService.getStats();

    }
}
