package com.example.web.BackendTests.RestApi;

import com.example.web.BackEnd.RestApi.UserModel;
import com.example.web.BackEnd.RestApi.UserRepository;
import com.example.web.BackEnd.RestApi.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.mockito.Mockito.when;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNull;

@SpringBootTest(classes = UserServiceTest.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testGetUserEmailAndPassword_Success() {
        //Given
        String username = "Tester";
        String email = "test@gmail.com";
        String password = "Test$12345";

        UserModel testExampleUser = new UserModel(username, email, password);

        userService.createUser(testExampleUser);

        when(userService.getUserByEmailAndPassword(email, password)).thenReturn(testExampleUser);

        //When
        UserModel retrievedUser = userService.getUserByEmailAndPassword(email, password);

        //Then
        assertNotNull(retrievedUser);
        assertEquals(username, retrievedUser.getUsername());
        assertEquals(email, retrievedUser.getEmail());
        assertEquals(password, retrievedUser.getPassword());
    }

    @Test
    public void testGetNotExistingUser_NotFound() {
        //Given
        String email = "NotExisting@gmail.com";
        String password = "NotExisting$12345";

        when(userService.getUserByEmailAndPassword(email, password)).thenReturn(null);

        //When
        UserModel retrievedUserNotFound = userService.getUserByEmailAndPassword(email, password);

        //Then
        assertNull(retrievedUserNotFound);
    }


    @Test
    public void testGetNullEmail_Invalid() {
        //Given
        String email = null;
        String password = "Test$12345";

        when(userService.getUserByEmailAndPassword(email, password)).thenReturn(null);

        //When
        UserModel retrievedUserNotFound = userService.getUserByEmailAndPassword(email, password);

        //Then
        assertNull(retrievedUserNotFound);
    }

    @Test
    public void testGetNullPassword_Invalid() {
        //Given
        String email = "test@gmail.com";
        String password = null;

        when(userService.getUserByEmailAndPassword(email, password)).thenReturn(null);

        //When
        UserModel retrievedUserNotFound = userService.getUserByEmailAndPassword(email, password);

        //Then
        assertNull(retrievedUserNotFound);
    }

    @Test
    public void testMismatchedPassword_Invalid() {
        //Given
        String username_1 = "Tester1";
        String email_1 = "test111@gmail.com";
        String password_1 = "TestPa$$word111";

        String email_2 = "test111@gmail.com";
        String password_2 = "TestPa$$word222";

        UserModel existingUserInSystem = new UserModel(username_1, email_1, password_1);

        userService.createUser(existingUserInSystem);

        when(userService.getUserByEmailAndPassword(email_2, password_2)).thenReturn(null);

        //When
        UserModel retrievedUser = userService.getUserByEmailAndPassword(email_2, password_2);

        //Then
        assertNull(retrievedUser);
    }

@Test
    public void testMismatchedEmail_Invalid() {
        //Given
        String username_1 = "Tester1";
        String email_1 = "test111@gmail.com";
        String password_1 = "TestPa$$word111";

        String email_2 = "test222@gmail.com";
        String password_2 = "TestPa$$word111";

        UserModel existingUserInSystem = new UserModel(username_1, email_1, password_1);

        userService.createUser(existingUserInSystem);

        when(userService.getUserByEmailAndPassword(email_2, password_2)).thenReturn(null);

        //When
        UserModel retrievedUser = userService.getUserByEmailAndPassword(email_2, password_2);

        //Then
        assertNull(retrievedUser);
    }







}
