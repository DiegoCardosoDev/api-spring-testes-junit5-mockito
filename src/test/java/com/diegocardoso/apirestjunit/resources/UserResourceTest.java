package com.diegocardoso.apirestjunit.resources;

import com.diegocardoso.apirestjunit.domain.User;
import com.diegocardoso.apirestjunit.domain.userDTO.UserDTO;
import com.diegocardoso.apirestjunit.services.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;


@SpringBootTest
class UserResourceTest {

    public static final Integer ID = 1;
    public static final String NAME = "deigo";
    public static final String EMAIL = "diego@dev";
    public static final String PASSWORD = "123";
    public static final int INDEX = 0;

    private User user;
    private UserDTO userDTO;


    @InjectMocks
    private UserResource resource;

    @Mock
    private UserServiceImpl service;
    @Mock
    private ModelMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void whebFindByidThenReturnSucess() {
        when(service.findById(anyInt())).thenReturn(user);
        when(mapper.map(any(), any())).thenReturn(userDTO);

        ResponseEntity<UserDTO> response = resource.findByid(ID);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(UserDTO.class, response.getBody().getClass());
        assertEquals(ID, response.getBody().getId());
        assertEquals(NAME, response.getBody().getName());
        assertEquals(EMAIL, response.getBody().getEmail());
        assertEquals(PASSWORD, response.getBody().getPassword());

    }

    @Test
    void whwnFindallThenReturnListofUserDto() {

        when(service.findAll()).thenReturn(List.of(user));
        when(mapper.map(any(), any())).thenReturn(userDTO);

        ResponseEntity<List<UserDTO>> response = resource.findAll();

        assertNotNull(response);//nao pode ser nulo
        assertNotNull(response.getBody());//o objeto no corpo nao pode nulo
        assertEquals(HttpStatus.OK, response.getStatusCode());//resposta deve ser ok(200)
        assertEquals(ResponseEntity.class, response.getClass());//espera um reponse entity
        assertEquals(ArrayList.class, response.getBody().getClass());//o corpo do reponse deve ser um array
        assertEquals(UserDTO.class, response.getBody().get(INDEX).getClass());//espera um user dto no indice 0
        assertEquals(ID, response.getBody().get(INDEX).getId());//o id deve ser o id do indice
        assertEquals(NAME, response.getBody().get(INDEX).getName());//o nome deve ser do indice
        assertEquals(EMAIL, response.getBody().get(INDEX).getEmail());//email deve ser do indice
        assertEquals(PASSWORD, response.getBody().get(INDEX).getPassword());//password deve ser do indice


    }


    @Test
    void whenCreateThenReturnCreated() {

        when(service.create(any())).thenReturn(user);
        ResponseEntity<UserDTO> response = resource.create(userDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getHeaders().get("Location"));
        assertEquals(ResponseEntity.class, response.getClass());

    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    private void startUser() {
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);


    }
}
