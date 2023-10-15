package com.example.web.BackendTest.RestApiTest.services;

import com.example.web.BackEnd.RestApi.models.BookModel;
import com.example.web.BackEnd.RestApi.models.UserModel;
import com.example.web.BackEnd.RestApi.repositories.BookRepository;
import com.example.web.BackEnd.RestApi.repositories.UserRepository;
import com.example.web.BackEnd.RestApi.services.User_BookService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = User_BookServiceTest.class)
public class User_BookServiceTest {

    @InjectMocks
    private User_BookService user_bookService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BookRepository bookRepository;

    @Test
    public void testSetUserToBook() {
        String title = "Title";
        String email = "example@example.com";

        BookModel book = Mockito.mock(BookModel.class);
        book.setTitle(title);
        book.setAuthor("Author");
        book.setAvailable(true);

        UserModel user = Mockito.mock(UserModel.class);
        user.setUsername("Example");
        user.setEmail(email);
        user.setPassword("Example$Pass123");

        when(userRepository.findByEmail(email)).thenReturn(user);
        when(bookRepository.findByTitle(title)).thenReturn(book);

        List<BookModel> bookModelList = new ArrayList<>();

        when(user.getBooks()).thenReturn(bookModelList);
        when(user.getEmail()).thenReturn(email);
        when(book.getTitle()).thenReturn(title);

        UserModel setUserToBook = user_bookService.addBookToUser(email, title);

        assertNotNull(setUserToBook.getBooks());
        assertEquals(1, setUserToBook.getBooks().size());
        assertEquals(title, setUserToBook.getBooks().iterator().next().getTitle());

        assertEquals(user.getUsername(), setUserToBook.getUsername());
        assertEquals(user.getEmail(), setUserToBook.getEmail());
        assertEquals(user.getPassword(), setUserToBook.getPassword());
    }

    @Test
    public void testUserNotFoundToBook() {
        String title = "Title";
        String email = "example@example.com";

        BookModel book = Mockito.mock(BookModel.class);
        book.setTitle(title);
        book.setAuthor("Author");
        book.setAvailable(true);

        UserModel user = Mockito.mock(UserModel.class);

        when(userRepository.findByEmail(email)).thenReturn(null);
        when(bookRepository.findByTitle(title)).thenReturn(book);

        List<BookModel> bookModelList = new ArrayList<>();

        when(user.getBooks()).thenReturn(bookModelList);
        when(user.getEmail()).thenReturn(null);
        when(book.getTitle()).thenReturn(title);

        UserModel userNotFound = user_bookService.addBookToUser(email, title);

        assertNull(userNotFound);

        verify(userRepository, times(1)).findByEmail(email);
        verify(bookRepository, times(1)).findByTitle(title);
    }

    @Test
    public void testUserToBookNotFound() {
        String title = "Title";
        String email = "example@example.com";

        BookModel book = Mockito.mock(BookModel.class);

        UserModel user = Mockito.mock(UserModel.class);
        user.setUsername("Example");
        user.setEmail(email);
        user.setPassword("Example$Pass123");

        when(userRepository.findByEmail(email)).thenReturn(user);
        when(bookRepository.findByTitle(title)).thenReturn(null);

        List<BookModel> bookModelList = new ArrayList<>();

        when(user.getBooks()).thenReturn(bookModelList);
        when(user.getEmail()).thenReturn(email);
        when(book.getTitle()).thenReturn(null);

        UserModel bookNotFound = user_bookService.addBookToUser(email, title);

        assertNull(bookNotFound);

        verify(userRepository, times(1)).findByEmail(email);
        verify(bookRepository, times(1)).findByTitle(title);
    }
    @Test
    public void testBothUserAndBookNotFound() {
        String title = "Title";
        String email = "example@example.com";

        BookModel book = Mockito.mock(BookModel.class);

        UserModel user = Mockito.mock(UserModel.class);

        when(userRepository.findByEmail(email)).thenReturn(null);
        when(bookRepository.findByTitle(title)).thenReturn(null);

        List<BookModel> bookModelList = new ArrayList<>();

        when(user.getBooks()).thenReturn(bookModelList);
        when(user.getEmail()).thenReturn(null);
        when(book.getTitle()).thenReturn(null);

        UserModel bookNotFound = user_bookService.addBookToUser(email, title);

        assertNull(bookNotFound);

        verify(userRepository, times(1)).findByEmail(email);
        verify(bookRepository, times(1)).findByTitle(title);
    }



}
