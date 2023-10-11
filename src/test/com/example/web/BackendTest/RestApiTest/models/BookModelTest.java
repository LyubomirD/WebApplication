package com.example.web.BackendTest.RestApiTest.models;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import com.example.web.BackEnd.RestApi.models.BookModel;
import com.example.web.BackEnd.RestApi.models.CategoryModel;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

@SpringBootTest(classes = BookModelTest.class)
public class BookModelTest {

    @Test
    public void testBookModelSaveInformation() {
        BookModel book = new BookModel();
        book.setTitle("Example");
        book.setAuthor("Test");
        book.setAvailable(true);

        assertEquals("Example", book.getTitle());
        assertEquals("Test", book.getAuthor());
        assertEquals(true, book.isAvailable());
    }

    @Test
    public void testBookToSaveCategory() {
        BookModel book = new BookModel();
        book.setTitle("Tales of terror");
        book.setAuthor("H.P. Lovecraft");
        book.setAvailable(true);

        CategoryModel mainCategory = new CategoryModel();
        mainCategory.setGenre("Horror");
        CategoryModel secondaryCategory = new CategoryModel();
        secondaryCategory.setGenre("Sci-fi");


        book.setCategories(Set.of(mainCategory, secondaryCategory));

        assertEquals("Tales of terror", book.getTitle());
        assertEquals("H.P. Lovecraft", book.getAuthor());
        assertEquals(true, book.isAvailable());
        assertNotNull(book.getCategories());
        assertEquals(2, book.getCategories().size());
    }




}
