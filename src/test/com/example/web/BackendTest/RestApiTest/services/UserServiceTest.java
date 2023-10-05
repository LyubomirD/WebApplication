package com.example.web.BackendTest.RestApiTest.services;

import com.example.web.BackEnd.RestApi.models.UserModel;
import com.example.web.BackEnd.RestApi.repositories.UserRepository;
import com.example.web.BackEnd.RestApi.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = UserServiceTest.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testGetUserByEmail() {
        String email = "test@example.com";

        UserModel mockUser = new UserModel();
        mockUser.setUsername("TestExample");
        mockUser.setEmail(email);

        when(userRepository.findByEmail(email)).thenReturn(mockUser);

        UserModel retrievedUser = userService.getUserByEmail(email);

        assertEquals("TestExample", retrievedUser.getUsername());
        assertEquals(email, retrievedUser.getEmail());
    }

    @Test
    public void testNoUserByEmail() {
        String email = "test@example.com";

        when(userRepository.findByEmail(email)).thenReturn(null);

        UserModel result = userService.getUserByEmail(email);

        assertNull(result);
    }

    @Test
    public void testAddNewNotExistingUser() {
        String email = "test@example.com";
        String password = "Test!Example123";

        UserModel mockUser = new UserModel();
        mockUser.setUsername("TestExample");
        mockUser.setEmail(email);
        mockUser.setPassword(password);

        when(userRepository.findByEmail(email)).thenReturn(null);
        when(passwordEncoder.encode(password)).thenReturn("hashedPassword");
        when(userRepository.save(mockUser)).thenReturn(mockUser);

        UserModel result = userService.addNewUser(mockUser);

        assertNotNull(result);
        assertEquals(email, result.getEmail());
        assertEquals("hashedPassword", result.getPassword());

        verify(userRepository, times(1)).findByEmail(email);
        verify(passwordEncoder, times(1)).encode(password);
        verify(userRepository, times(1)).save(mockUser);
    }

    @Test
    public void testExistingUser() {
        String email = "test@example.com";
        String password = "Test!Example123";

        UserModel existingMockUser = new UserModel();
        existingMockUser.setUsername("TestExample");
        existingMockUser.setEmail(email);
        existingMockUser.setPassword(password);

        when(userRepository.findByEmail(email)).thenReturn(existingMockUser);

        UserModel result = userService.addNewUser(existingMockUser);

        assertNull(result);
    }

}
