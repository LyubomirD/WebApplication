package com.example.web.BackendTest.RestApiTest.controllers;

import com.example.web.BackEnd.RestApi.controllers.UserController;
import com.example.web.BackEnd.RestApi.models.UserModel;
import com.example.web.BackEnd.RestApi.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = UserControllerTest.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Mock
    private BindingResult bindingResult;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateValidUser() {
        UserModel mockUser = new UserModel();
        mockUser.setUsername("TestExample");
        mockUser.setEmail("test@example.com");
        mockUser.setPassword("Test!Example123");

        when(userService.addNewUser(mockUser)).thenReturn(mockUser);

        ResponseEntity<UserModel> responseEntity = userController.createUser(mockUser, bindingResult);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(mockUser, responseEntity.getBody());
    }

    @Test
    public void testCreateInvalidNullUser() {
        UserModel mockUser = null;

        when(userService.addNewUser(mockUser)).thenReturn(mockUser);

        ResponseEntity<UserModel> responseEntity = userController.createUser(mockUser, bindingResult);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals(mockUser, responseEntity.getBody());
    }

    // Do not know how to preform test on login (auth) user

}
