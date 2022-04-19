package co.com.adn.adapter.router.controller;

import co.com.adn.AbstractTest;
import co.com.adn.adapter.router.RouterConsts;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

public class SwaggerControllerTest extends AbstractTest {

    @Test
    public void testGetUrl() throws Exception {

        String pathUrl = RouterConsts.INITIAL_PATH;

        final MockHttpServletResponse response = mockMvc.perform(get(pathUrl)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }


    @Test
    public void testGetToken() throws Exception {

        String pathUrl = RouterConsts.CSRF_PATH;

        final MockHttpServletResponse response = mockMvc.perform(get(pathUrl)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }
}
