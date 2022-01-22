package com.diegocardoso.apirestjunit.resources.exeptions;

import com.diegocardoso.apirestjunit.services.exeptions.DataIntegrityViolationExeption;
import com.diegocardoso.apirestjunit.services.exeptions.ObjectNotFoundExeption;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ResourceExeptionHandlerTest {


    public static final String OBJETO_NAO_ENCONTRADO = "objeto não encontrado";
    public static final String EMAIL_JA_CADASTRADO = "email ja cadastrado";

    @InjectMocks
    private ResourceExeptionHandler exeptionHandler;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void wheObjectNotFoundThenReturnResponseentityExeption() {

        ResponseEntity<StandardError> response = exeptionHandler.objectNotFound(new ObjectNotFoundExeption(OBJETO_NAO_ENCONTRADO), new MockHttpServletRequest());


        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandardError.class, response.getBody().getClass());
        assertEquals(OBJETO_NAO_ENCONTRADO, response.getBody().getError());
        assertEquals(404, response.getBody().getStatus());
        assertNotEquals("/user/2", response.getBody().getPath());
        assertNotEquals(LocalDateTime.now(), response.getBody().getTimestamp());


    }

    @Test
    void whenDataIntegrityViolationExeption() {

        ResponseEntity<StandardError> response = exeptionHandler.dataIntegration(new DataIntegrityViolationExeption(EMAIL_JA_CADASTRADO), new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandardError.class, response.getBody().getClass());
        assertEquals(EMAIL_JA_CADASTRADO, response.getBody().getError());
        assertEquals(400, response.getBody().getStatus());


    }
}