package com.example.web.BackendTest.RestApiTest.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.web.BackEnd.RestApi.models.BookModel;
import com.example.web.BackEnd.RestApi.models.UserModel;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest(classes = UserModelTest.class)
public class UserModelTest {

    @Test
    public void testUserModel() {
        UserModel user = new UserModel();
        user.setUsername("TestExample");
        user.setEmail("test@example.com");
        user.setPassword("Test!Example123");

        assertEquals("TestExample", user.getUsername());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("Test!Example123", user.getPassword());
    }

    @Test
    public void testUserModelWithBooks() {
        UserModel user = new UserModel();
        user.setUsername("TestExample");
        user.setEmail("test@example.com");
        user.setPassword("Test!Example123");

        BookModel book1 = new BookModel();
        book1.setTitle("Book 1");
        BookModel book2 = new BookModel();
        book2.setTitle("Book 2");
        user.setBooks(Arrays.asList(book1, book2));

        assertEquals("TestExample", user.getUsername());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("Test!Example123", user.getPassword());
        assertNotNull(user.getBooks());
        assertEquals(2, user.getBooks().size());
    }
}
