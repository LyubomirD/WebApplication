package com.example.web.BackendTest.RestApiTest.controllers;

import com.example.web.BackEnd.RestApi.controllers.BookToCategoryController;
import com.example.web.BackEnd.RestApi.models.BookModel;
import com.example.web.BackEnd.RestApi.services.BookToCategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = BookToCategoryControllerTest.class)
public class BookToCategoryControllerTest {

    @InjectMocks
    private BookToCategoryController bookTocategoryController;

    @Mock
    private BookToCategoryService bookTocategoryService;

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

        when(bookTocategoryService.setBookGenre(genre, title)).thenReturn(mockBook);

        ResponseEntity<BookModel> responseEntity = bookTocategoryController.setBookCategory(genre, title);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockBook, responseEntity.getBody());
    }

    @Test
    public void testSetBookCategory_NotFound() {
        String genre = "NonexistentGenre";
        String title = "NonexistentTitle";

        when(bookTocategoryService.setBookGenre(genre, title)).thenReturn(null);

        ResponseEntity<BookModel> responseEntity = bookTocategoryController.setBookCategory(genre, title);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }


}
