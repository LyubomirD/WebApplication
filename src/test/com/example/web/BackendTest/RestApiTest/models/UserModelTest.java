package com.example.web.BackendTest.RestApiTest.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.web.BackEnd.RestApi.models.BookModel;
import com.example.web.BackEnd.RestApi.models.CategoryModel;
import com.example.web.BackEnd.RestApi.models.UserModel;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@SpringBootTest(classes = UserModelTest.class)
public class UserModelTest {

    @Test
    public void testUserModelSaveInformation() {
        UserModel user = new UserModel();
        user.setUsername("TestExample");
        user.setEmail("test@example.com");
        user.setPassword("Test!Example123");

        assertEquals("TestExample", user.getUsername());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("Test!Example123", user.getPassword());
    }

    @Test
    public void testUserModelToSaveBooks() {
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

    @Test
    public void testUserModelSaveBookAndCategory() {
        UserModel user = new UserModel();
        user.setUsername("TestExample");
        user.setEmail("test@example.com");
        user.setPassword("Test!Example123");

        CategoryModel romance = new CategoryModel();
        romance.setGenre("Romance");
        CategoryModel horror = new CategoryModel();
        horror.setGenre("Horror");
        CategoryModel fantasy = new CategoryModel();
        fantasy.setGenre("Fantasy");

        BookModel theHistorian = new BookModel();
        theHistorian.setTitle("The Historian");
        theHistorian.setCategories(Set.of(romance, horror));

        BookModel theDarkTower = new BookModel();
        theDarkTower.setTitle("The Dark Tower");
        theDarkTower.setCategories(Set.of(horror, fantasy));

        user.setBooks(Arrays.asList(theHistorian, theDarkTower));

        assertEquals("TestExample", user.getUsername());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("Test!Example123", user.getPassword());
        assertNotNull(user.getBooks());
        assertEquals(2, user.getBooks().size());
        Set<CategoryModel> darkTowerCategories = theDarkTower.getCategories();
        Set<CategoryModel> historianCategories = theHistorian.getCategories();
        assertTrue(darkTowerCategories.stream().anyMatch(category -> "Horror".equals(category.getGenre())));
        assertTrue(darkTowerCategories.stream().anyMatch(category -> "Fantasy".equals(category.getGenre())));
        assertTrue(historianCategories.stream().anyMatch(category -> "Horror".equals(category.getGenre())));
        assertTrue(historianCategories.stream().anyMatch(category -> "Romance".equals(category.getGenre())));
    }
}
