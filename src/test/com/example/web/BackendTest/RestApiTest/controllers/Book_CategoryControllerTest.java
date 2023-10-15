package com.example.web.BackendTest.RestApiTest.controllers;

import com.example.web.BackEnd.RestApi.controllers.BookController;
import com.example.web.BackEnd.RestApi.controllers.Book_CategoryController;
import com.example.web.BackEnd.RestApi.models.BookModel;
import com.example.web.BackEnd.RestApi.models.CategoryModel;
import com.example.web.BackEnd.RestApi.services.BookService;
import com.example.web.BackEnd.RestApi.services.Book_CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = Book_CategoryControllerTest.class)
public class Book_CategoryControllerTest {

    @InjectMocks
    private Book_CategoryController book_categoryController;

    @Mock
    private Book_CategoryService book_categoryService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSetBookCategory_Success() {
        String genre = "Mystery";
        String title = "The Lost Key";
        BookModel mockBook = new BookModel();
        mockBook.setTitle(title);
        mockBook.setAuthor("John Doe");
        mockBook.setAvailable(true);

        when(book_categoryService.setBookGenre(genre, title)).thenReturn(mockBook);

        ResponseEntity<BookModel> responseEntity = book_categoryController.setBookCategory(genre, title);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockBook, responseEntity.getBody());
    }

    @Test
    public void testSetBookCategory_NotFound() {
        String genre = "NonexistentGenre";
        String title = "NonexistentTitle";

        when(book_categoryService.setBookGenre(genre, title)).thenReturn(null);

        ResponseEntity<BookModel> responseEntity = book_categoryController.setBookCategory(genre, title);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }


}
