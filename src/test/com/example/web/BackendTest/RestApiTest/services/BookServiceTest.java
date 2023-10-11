package com.example.web.BackendTest.RestApiTest.services;

import com.example.web.BackEnd.CustomException.DuplicateBookException;
import com.example.web.BackEnd.RestApi.models.BookModel;
import com.example.web.BackEnd.RestApi.repositories.BookRepository;
import com.example.web.BackEnd.RestApi.services.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = BookServiceTest.class)
public class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddNewBookWithUniqueTitleAndAuthor() {
        String title = "Sherlock Holmes";
        String author = "Sir. Arthur Conan Doyle";

        BookModel mockBook = new BookModel();
        mockBook.setTitle(title);
        mockBook.setAuthor(author);

        when(bookRepository.findByTitleAndAuthor(title, author)).thenReturn(null);
        when(bookRepository.save(mockBook)).thenReturn(mockBook);

        BookModel addedBook = bookService.addNewBook(mockBook);

        assertEquals(mockBook.getTitle(), addedBook.getTitle());
        assertEquals(mockBook.getAuthor(), addedBook.getAuthor());
    }

    @Test
    public void testAddNewBookWithDuplicateTitleAndAuthor() {
        String title = "Sherlock Holmes";
        String author = "Sir. Arthur Conan Doyle";

        BookModel existingBook = new BookModel();
        existingBook.setTitle(title);
        existingBook.setAuthor(author);

        when(bookRepository.findByTitleAndAuthor(title, author)).thenReturn(existingBook);

        try {
            bookService.addNewBook(existingBook);
            fail("Expected DuplicateBookException");
        } catch (DuplicateBookException e) {
            assertEquals("A book with the same title and author already exists.", e.getMessage());
        }
    }


    @Test
    public void testAddNewBookWithSameTitleDifferentAuthor() {
        String title = "The Deep";
        String authorOne = "Peter Benchley";
        String authorTwo = "Helen Dunmore";

        BookModel existingBook = new BookModel();
        existingBook.setTitle(title);
        existingBook.setAuthor(authorOne);

        BookModel mockBook = new BookModel();
        mockBook.setTitle(title);
        mockBook.setAuthor(authorTwo);

        when(bookRepository.findByTitleAndAuthor(title, authorOne)).thenReturn(null);
        when(bookRepository.save(mockBook)).thenReturn(mockBook);

        BookModel addedBook = bookService.addNewBook(mockBook);

        assertEquals(existingBook.getTitle(), addedBook.getTitle());
        assertNotEquals(existingBook.getAuthor(), addedBook.getAuthor());
    }

    @Test
    public void testUpdateBookWithValidId() {
        int id = 1;
        BookModel existingBook = new BookModel();
        existingBook.setBook_id(id);
        existingBook.setTitle("Title");
        existingBook.setAuthor("Author");
        existingBook.setAvailable(true);

        BookModel updatedBook = new BookModel();
        updatedBook.setBook_id(id);
        updatedBook.setAvailable(false);

        when(bookRepository.findById(id)).thenReturn(Optional.of(existingBook));
        when(bookRepository.save(existingBook)).thenReturn(updatedBook);

        BookModel result = bookService.updateBook(id, updatedBook);

        assertEquals(id, result.getBook_id());
        assertEquals(updatedBook.getTitle(), result.getTitle());
        assertEquals(updatedBook.getAuthor(), result.getAuthor());
        assertEquals(updatedBook.isAvailable(), result.isAvailable());
    }

    @Test
    public void testUpdateBookWithInvalidId() {
        int id = 1;
        BookModel updatedBook = new BookModel();
        updatedBook.setBook_id(id);
        updatedBook.setTitle("New Title");
        updatedBook.setAuthor("New Author");
        updatedBook.setAvailable(false);

        when(bookRepository.findById(id)).thenReturn(Optional.empty());

        BookModel result = bookService.updateBook(id, updatedBook);

        assertNull(result);
    }

    @Test
    public void testRemoveBook() {
        boolean available = false;
        List<BookModel> booksToRemove = new ArrayList<>();
        booksToRemove.add(new BookModel());
        booksToRemove.add(new BookModel());

        when(bookRepository.findByAvailable(available)).thenReturn(booksToRemove);

        bookService.removeBook(available);

        verify(bookRepository, times(1)).deleteAll(booksToRemove);
    }

}
