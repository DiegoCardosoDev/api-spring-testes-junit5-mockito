package com.diegocardoso.apirestjunit.services.impl;

import com.diegocardoso.apirestjunit.domain.User;
import com.diegocardoso.apirestjunit.domain.userDTO.UserDTO;
import com.diegocardoso.apirestjunit.repositories.UserRepository;
import com.diegocardoso.apirestjunit.services.exeptions.DataIntegrateViolationExeption;
import com.diegocardoso.apirestjunit.services.exeptions.ObjectNotFoundExeption;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceImplTest {

    public static final Integer ID = 1;
    public static final String NAME = "deigo";
    public static final String EMAIL = "diego@dev";
    public static final String PASSWORD = "123";
    @SuppressWarnings("NonAsciiCharacters")
    public static final String OBJETO_NÃO_ENCONTRADO = "objeto não encontrado";
    public static final String EMAIL_JÁ_CADASTRADO = "email já cadastrado!";

    @InjectMocks
    private UserServiceImpl service;

    @Mock
    private UserRepository repository;

    @Mock
    private ModelMapper mapper;

    private User user;
    private UserDTO userDTO;
    private Optional<User> optionalUser;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();

    }

    @Test
    void whenFindByIdThenReturnAnUserInstance() {
        when(repository.findById(anyInt())).thenReturn(optionalUser);


        User response = service.findById(ID);

        assertNotNull(response);//-espera que nao seja nulo
        assertEquals(User.class, response.getClass());//-espera a classe USER
        assertEquals(ID, response.getId());//-TESTA O ID
        assertEquals(NAME, response.getName());//-TESTA O NAME
        assertEquals(EMAIL, response.getEmail());//-TESTA O EMAIL
    }


    @Test
    void whenFindByIdThenreturnAnObjectNotFoundExdeption() {//met
        when(repository.findById(anyInt())).thenThrow(new ObjectNotFoundExeption(OBJETO_NÃO_ENCONTRADO));

        try {
            service.findById(ID);
        } catch (Exception exception) {
            assertEquals(ObjectNotFoundExeption.class, exception.getClass());
            assertEquals(OBJETO_NÃO_ENCONTRADO, exception.getMessage());
        }

    }

    @Test
    void whenfindallThenReturnListOfUsers() {

        when(repository.findAll()).thenReturn(List.of(user));

        List<User> listResponse = service.findAll();

        assertNotNull(listResponse);
        assertEquals(1, listResponse.size());
        assertEquals(User.class, listResponse.get(0).getClass());
        assertEquals(ID, listResponse.get(0).getId());
        assertEquals(NAME, listResponse.get(0).getName());
        assertEquals(EMAIL, listResponse.get(0).getEmail());
        assertEquals(PASSWORD, listResponse.get(0).getPassword());
    }

    @Test
    void whenCreateThesReturnSucess() {
        when(repository.save(any())).thenReturn(user);

        User reponse = service.create(userDTO);
        assertNotNull(reponse);
        assertEquals(User.class, reponse.getClass());
        assertEquals(ID, reponse.getId());
        assertEquals(NAME, reponse.getName());
        assertEquals(EMAIL, reponse.getEmail());
        assertEquals(PASSWORD, reponse.getPassword());

    }

    @Test
    void whenCreateThesReturnDataIntegrityViolationExeption() {
        when(repository.findByEmail(anyString())).thenReturn(optionalUser);

        try {

            optionalUser.get().setId(2);
            service.create(userDTO);

        } catch (Exception ex) {
            assertEquals(DataIntegrateViolationExeption.class, ex.getClass());
            assertEquals(EMAIL_JÁ_CADASTRADO, ex.getMessage());
        }


    }


    @Test
    void whenUpdateThesReturnSucess() {
        when(repository.save(any())).thenReturn(user);

        User reponse = service.update(userDTO);
        assertNotNull(reponse);
        assertEquals(User.class, reponse.getClass());
        assertEquals(ID, reponse.getId());
        assertEquals(NAME, reponse.getName());
        assertEquals(EMAIL, reponse.getEmail());
        assertEquals(PASSWORD, reponse.getPassword());

    }

    @Test
    void whenUpdateThesReturnDataIntegrityViolationExeption() {
        when(repository.findByEmail(anyString())).thenReturn(optionalUser);

        try {

            optionalUser.get().setId(2);
            service.create(userDTO);

        } catch (Exception ex) {
            assertEquals(DataIntegrateViolationExeption.class, ex.getClass());
            assertEquals(EMAIL_JÁ_CADASTRADO, ex.getMessage());
        }


    }

    @Test
    void deleteWhenSucsess() {

        when(repository.findById(anyInt())).thenReturn(optionalUser);

        doNothing().when(repository).deleteById(anyInt());
        service.delete(ID);
        verify(repository, times(1)).deleteById(anyInt());
    }

    private void startUser() {
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
        optionalUser = Optional.of(new User(ID, NAME, EMAIL, PASSWORD));


    }
}