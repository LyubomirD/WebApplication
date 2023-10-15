package com.example.web.BackendTest.RestApiTest.controllers;

import com.example.web.BackEnd.RestApi.controllers.User_BookController;
import com.example.web.BackEnd.RestApi.models.BookModel;
import com.example.web.BackEnd.RestApi.models.UserModel;
import com.example.web.BackEnd.RestApi.services.User_BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = User_BookControllerTest.class)
public class User_BookControllerTest {

    @InjectMocks
    private User_BookController user_BookController;

    @Mock
    private User_BookService user_BookService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testUserGetsBook() {
        String email = "testuser@example.com";
        String title = "Test Book Title";
        UserModel mockUser = new UserModel();

        when(user_BookService.addBookToUser(email, title)).thenReturn(mockUser);

        ResponseEntity<UserModel> responseEntity = user_BookController.userGetsBook(email, title);

        Mockito.verify(user_BookService, times(1)).addBookToUser(email, title);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockUser, responseEntity.getBody());
    }

    @Test
    public void testSetUserBook_NotFound() {
        String email = "testuser@example.com";
        String title = "Test Book Title";
        UserModel mockUser = new UserModel();

        when(user_BookService.addBookToUser(email, title)).thenReturn(null );

        ResponseEntity<UserModel> responseEntity = user_BookController.userGetsBook(email, title);

        Mockito.verify(user_BookService, times(1)).addBookToUser(email, title);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
}
