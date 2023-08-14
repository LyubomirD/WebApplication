package com.example.web.BackendTests.RestApi;

import com.example.web.BackEnd.RestApi.UserController;
import com.example.web.BackEnd.RestApi.UserModel;
import com.example.web.BackEnd.RestApi.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.AssertJUnit.*;

@SpringBootTest(classes = UserControllerTest.class)
public class UserControllerTest {

    private BindingResult bindingResult;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    public void testGetUser_Success() {
        // Given
        String testEmail = "test_success@mail.com";
        String testPassword = "Test123$";

        UserModel expectedUser = new UserModel("TestUser", testEmail, testPassword);

        when(userService.getUserByEmailAndPassword(eq(testEmail), eq(testPassword))).thenReturn(expectedUser);

        // When
        ResponseEntity<UserModel> response = userController.getUser(testEmail, testPassword);

        // Then
        assertEquals(expectedUser, response.getBody());
        assertSame(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGetUser_NotFound() {
        // Given
        String testEmail = "non_existent@mail.com";
        String testPassword = "Invalid123$";

        when(userService.getUserByEmailAndPassword(eq(testEmail), eq(testPassword))).thenReturn(null);

        // When
        ResponseEntity<UserModel> response = userController.getUser(testEmail, testPassword);

        // Then
        assertNull(response.getBody());
        assertSame(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testPostUser_Success() {
        //Given
        String testUsername = "TesterSuccess";
        String testEmail = "test_success@mail.com";
        String testPassword = "Test123$";

        UserModel testUserModel = new UserModel(testUsername, testEmail, testPassword);

        when(userService.createUser(testUserModel)).thenReturn(testUserModel);

        // Initialize BindingResult
        bindingResult = new org.springframework.validation.BeanPropertyBindingResult(testUserModel, "testUserModelSuccess");

        // When
        ResponseEntity<UserModel> response = userController.createUser(testUserModel, bindingResult);

        // Then
        assertEquals(testUserModel, response.getBody());
        assertSame(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void testCreateUser_WithValidationErrors() {
        //Given
        String testUsername = "Tester&";
        String testEmail = "invalid.mail.com";
        String testPassword = "invalid";

        UserModel testUserModel = new UserModel(testUsername, testEmail, testPassword);
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);

        //When
        ResponseEntity<UserModel> response = userController.createUser(testUserModel, bindingResult);

        //Then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testCreateUser_InternalServerError() {
        //Given
        String testUsername = null;
        String testEmail = null;
        String testPassword = null;

        UserModel testUserModel = new UserModel(testUsername, testEmail, testPassword);
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);
        when(userService.createUser(testUserModel)).thenReturn(null);

        //When
        ResponseEntity<UserModel> response = userController.createUser(testUserModel, bindingResult);

        //Then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }


}