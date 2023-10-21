package com.example.web.BackendTest.RestApiTest.controllers;

import com.example.web.BackEnd.RestApi.controllers.UserToBookController;
import com.example.web.BackEnd.RestApi.models.BookModel;
import com.example.web.BackEnd.RestApi.models.UserModel;
import com.example.web.BackEnd.RestApi.services.UserToBookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = UserToBookControllerTest.class)
public class UserToBookControllerTest {

    @InjectMocks
    private UserToBookController userToBookController;

    @Mock
    private UserToBookService userToBookService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testValidBookToUserList() throws Exception {
        String email = "lyubomir@gmail.com";
        String password = "Lyubomir!$12345";
        String title = "BookTitle";

        UserModel mockUser = new UserModel();
        mockUser.setUsername("Lyubomir");
        mockUser.setEmail(email);
        mockUser.setPassword(password);

        UserDetails userDetails = new User(email, password, Collections.emptyList());
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        when(userToBookService.addBookToUser(email, title)).thenReturn(mockUser);

        ResponseEntity<UserModel> responseEntity = userToBookController.addBookToUserList(title);

        verify(userToBookService).addBookToUser(email, title);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testNotExistingBookCantAddToUserList() {
        String email = "lyubomir@gmail.com";
        String password = "Lyubomir!$12345";
        String nonExistingTitle = "NonExistingBook";

        UserModel mockUser = new UserModel();
        mockUser.setUsername("Lyubomir");
        mockUser.setEmail(email);
        mockUser.setPassword(password);

        UserDetails userDetails = new User(email, password, Collections.emptyList());
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        when(userToBookService.addBookToUser(email, nonExistingTitle)).thenReturn(null);

        ResponseEntity<UserModel> responseEntity = userToBookController.addBookToUserList(nonExistingTitle);

        verify(userToBookService).addBookToUser(email, nonExistingTitle);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void testRemoveBookFromUserList() {
        String email = "lyubomir@gmail.com";
        String password = "Lyubomir!$12345";
        String title = "BookTitle";

        UserModel mockUser = new UserModel();
        mockUser.setUsername("Lyubomir");
        mockUser.setEmail(email);
        mockUser.setPassword(password);

        UserDetails userDetails = new User(email, password, Collections.emptyList());
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        when(userToBookService.removeBookFromUser(email, title)).thenReturn(mockUser);

        ResponseEntity<UserModel> responseEntity = userToBookController.removeBookFromUserList(title);

        verify(userToBookService).removeBookFromUser(email, title);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testRemoveNotExistingBookFromUserList() {
        String email = "lyubomir@gmail.com";
        String password = "Lyubomir!$12345";
        String nonExistingTitle = "NonExistingBook";

        UserModel mockUser = new UserModel();
        mockUser.setUsername("Lyubomir");
        mockUser.setEmail(email);
        mockUser.setPassword(password);

        UserDetails userDetails = new User(email, password, Collections.emptyList());
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        when(userToBookService.removeBookFromUser(email, nonExistingTitle)).thenReturn(null);

        ResponseEntity<UserModel> responseEntity = userToBookController.removeBookFromUserList(nonExistingTitle);

        verify(userToBookService).removeBookFromUser(email, nonExistingTitle);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void testAddingNullBookToUserList() {
        String email = "lyubomir@gmail.com";
        String password = "Lyubomir!$12345";
        String titleNull = null;

        UserModel mockUser = new UserModel();
        mockUser.setUsername("Lyubomir");
        mockUser.setEmail(email);
        mockUser.setPassword(password);

        UserDetails userDetails = new User(email, password, Collections.emptyList());
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        when(userToBookService.addBookToUser(email, titleNull)).thenReturn(null);

        ResponseEntity<UserModel> responseEntity = userToBookController.addBookToUserList(titleNull);

        verify(userToBookService).addBookToUser(email, titleNull);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void testRemovingNullBookToUserList() {
        String email = "lyubomir@gmail.com";
        String password = "Lyubomir!$12345";
        String titleNull = null;

        UserModel mockUser = new UserModel();
        mockUser.setUsername("Lyubomir");
        mockUser.setEmail(email);
        mockUser.setPassword(password);

        UserDetails userDetails = new User(email, password, Collections.emptyList());
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        when(userToBookService.removeBookFromUser(email, titleNull)).thenReturn(null);

        ResponseEntity<UserModel> responseEntity = userToBookController.removeBookFromUserList(titleNull);

        verify(userToBookService).removeBookFromUser(email, titleNull);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

}
