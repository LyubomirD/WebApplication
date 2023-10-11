package com.example.web.BackendTest.RestApiTest.models;


import com.example.web.BackEnd.RestApi.models.BookModel;
import com.example.web.BackEnd.RestApi.models.CategoryModel;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    public void testBookSaveInformationAndCategories() {
        BookModel talesOfTerror = new BookModel();
        talesOfTerror.setTitle("Tales of terror");
        talesOfTerror.setAuthor("H.P. Lovecraft");
        talesOfTerror.setAvailable(true);

        BookModel romeoAndJuliet = new BookModel();
        romeoAndJuliet.setTitle("Romeo And Juliet");
        romeoAndJuliet.setAuthor("William Shakespeare");
        romeoAndJuliet.setAvailable(false);

        CategoryModel horror = new CategoryModel();
        horror.setGenre("Horror");
        CategoryModel sci_fi = new CategoryModel();
        sci_fi.setGenre("Sci-fi");
        CategoryModel romance = new CategoryModel();
        romance.setGenre("Romance");
        CategoryModel classic = new CategoryModel();
        classic.setGenre("Classic");

        talesOfTerror.setCategories(Set.of(horror, sci_fi));
        romeoAndJuliet.setCategories(Set.of(romance, classic));

        assertEquals("Tales of terror", talesOfTerror.getTitle());
        assertEquals("H.P. Lovecraft", talesOfTerror.getAuthor());
        assertEquals(true, talesOfTerror.isAvailable());
        assertNotNull(talesOfTerror.getCategories());
        assertEquals(2, talesOfTerror.getCategories().size());

        assertEquals("Romeo and Juliet", romeoAndJuliet.getTitle());
        assertEquals("William Shakespeare", romeoAndJuliet.getAuthor());
        assertEquals(false, romeoAndJuliet.isAvailable());
        assertNotNull(romeoAndJuliet.getCategories());
        assertEquals(2, romeoAndJuliet.getCategories().size());

        Set<CategoryModel> talesOfTerrorCategories = talesOfTerror.getCategories();
        Set<CategoryModel> romeoAndJulietCategories = romeoAndJuliet.getCategories();

        assertTrue(talesOfTerrorCategories.stream().anyMatch(categoryModel -> "Horror".equals(categoryModel.getGenre())));
        assertTrue(talesOfTerrorCategories.stream().anyMatch(categoryModel -> "Sci-fi".equals(categoryModel.getGenre())));
        assertTrue(romeoAndJulietCategories.stream().anyMatch(categoryModel -> "Romance".equals(categoryModel.getGenre())));
        assertTrue(romeoAndJulietCategories.stream().anyMatch(categoryModel -> "Classic".equals(categoryModel.getGenre())));
    }
}
