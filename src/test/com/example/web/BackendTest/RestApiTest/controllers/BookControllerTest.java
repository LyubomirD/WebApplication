package com.example.web.BackendTest.RestApiTest.controllers;

import com.example.web.BackEnd.RestApi.controllers.BookController;
import com.example.web.BackEnd.RestApi.models.BookModel;
import com.example.web.BackEnd.RestApi.repositories.BookRepository;
import com.example.web.BackEnd.RestApi.services.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = BookControllerTest.class)
public class BookControllerTest {

    @InjectMocks
    private BookController bookController;

    @Mock
    private BookService bookService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddingBook() {
        BookModel mockBook = new BookModel();
        mockBook.setTitle("Sherlock Holmes");
        mockBook.setAuthor("Sir. Arthur Conan Doyle");
        mockBook.setAvailable(true);

        when(bookService.addNewBook(mockBook)).thenReturn(mockBook);

        ResponseEntity<BookModel> responseEntity = bookController.addBook(mockBook);

        verify(bookService).addNewBook(mockBook);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(mockBook, responseEntity.getBody());
    }

    @Test
    public void testUpdateBook() {
        int bookId = 1;
        BookModel updatedBook = new BookModel();
        updatedBook.setTitle("Updated Title");
        updatedBook.setAuthor("Updated Author");
        updatedBook.setAvailable(true);

        when(bookService.updateBook(eq(bookId), any(BookModel.class))).thenReturn(updatedBook);

        ResponseEntity<BookModel> responseEntity = bookController.updateBook(bookId, updatedBook);

        verify(bookService).updateBook(eq(bookId), any(BookModel.class));
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(updatedBook, responseEntity.getBody());
    }


    @Test
    public void testRemoveUnavailableBook() {
        boolean available = false;

        bookController.removeUnavailableBook(available);

        verify(bookService).removeBook(eq(available));
    }

}
