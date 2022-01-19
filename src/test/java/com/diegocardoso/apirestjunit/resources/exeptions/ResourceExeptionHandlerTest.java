package com.diegocardoso.apirestjunit.resources.exeptions;

import com.diegocardoso.apirestjunit.services.exeptions.ObjectNotFoundExeption;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ResourceExeptionHandlerTest {


    public static final String OBJETO_NAO_ENCONTRADO = "objeto não encontrado";
    
    @InjectMocks
    private ResourceExeptionHandler exeptionHandler;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void wheObjectNotFoundThenReturnResponseentityExeption() {

        ResponseEntity<StandardError> response = exeptionHandler
                .objectNotFound(new ObjectNotFoundExeption(OBJETO_NAO_ENCONTRADO),
                        new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandardError.class, response.getBody().getClass());
        assertEquals(OBJETO_NAO_ENCONTRADO, response.getBody().getError());
        assertEquals(404, response.getBody().getStatus());


    }

    @Test
    void dataIntegration() {
    }
}