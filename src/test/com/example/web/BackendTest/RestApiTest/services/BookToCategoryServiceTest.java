package com.example.web.BackendTest.RestApiTest.services;

import com.example.web.BackEnd.RestApi.models.BookModel;
import com.example.web.BackEnd.RestApi.models.CategoryModel;
import com.example.web.BackEnd.RestApi.repositories.BookRepository;
import com.example.web.BackEnd.RestApi.repositories.CategoryRepository;
import com.example.web.BackEnd.RestApi.services.BookToCategoryService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = BookToCategoryServiceTest.class)
public class BookToCategoryServiceTest {

    @InjectMocks
    private BookToCategoryService bookTocategoryService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Test
    public void testSetCategoryToBook() {
        String genre = "Genre";
        String title = "Title";
        String author = "Author";

        CategoryModel category = new CategoryModel();
        category.setGenre(genre);

        BookModel book = new BookModel();
        book.setTitle(title);
        book.setAuthor(author);
        book.setAvailable(true);

        when(bookRepository.findByTitle(title)).thenReturn(book);
        when(categoryRepository.findByGenre(genre)).thenReturn(category);

        BookModel setBookIntoCategory = bookTocategoryService.setBookGenre(genre, title);

        assertNotNull(setBookIntoCategory.getCategories());
        assertEquals(1, setBookIntoCategory.getCategories().size());
        assertEquals(genre, setBookIntoCategory.getCategories().iterator().next().getGenre());

        assertEquals(book.getTitle(), setBookIntoCategory.getTitle());
        assertEquals(book.getAuthor(), setBookIntoCategory.getAuthor());
        assertEquals(book.isAvailable(), setBookIntoCategory.isAvailable());
    }

    @Test
    public void testAddNewCategoryToExistingBook() {
        String genre = "Genre";
        String newGenre = "newGenre";
        String title = "Title";
        String author = "Author";

        CategoryModel category = new CategoryModel();
        category.setGenre(genre);

        CategoryModel secondCategory = new CategoryModel();
        secondCategory.setGenre(newGenre);

        BookModel book = new BookModel();
        book.setTitle(title);
        book.setAuthor(author);
        book.setAvailable(true);

        when(bookRepository.findByTitle(title)).thenReturn(book);
        when(categoryRepository.findByGenre(genre)).thenReturn(category);
        when(categoryRepository.findByGenre(newGenre)).thenReturn(secondCategory);

        BookModel existingBook = bookTocategoryService.setBookGenre(genre, title);
        existingBook = bookTocategoryService.setBookGenre(newGenre, title);

        assertNotNull(existingBook.getCategories());
        assertNotEquals(existingBook.getCategories().isEmpty(), true);
        assertEquals(2, existingBook.getCategories().size());

        Set<String> categories = existingBook.getCategories().stream()
                .map(CategoryModel::getGenre)
                .collect(Collectors.toSet());

        assertTrue(categories.contains(genre));
        assertTrue(categories.contains(newGenre));

        assertEquals(book.getTitle(), existingBook.getTitle());
        assertEquals(book.getAuthor(), existingBook.getAuthor());
        assertEquals(book.isAvailable(), existingBook.isAvailable());
    }

    @Test
    public void testNotExistingBookEqualsToNullAddCategory() {
        String genre = "Genre";
        String notExistingTitle = "NotExistingTitle";

        CategoryModel category = new CategoryModel();
        category.setGenre(genre);

        when(categoryRepository.findByGenre(genre)).thenReturn(category);
        when(bookRepository.findByTitle(notExistingTitle)).thenReturn(null);

        BookModel notExistingBook = bookTocategoryService.setBookGenre(genre, notExistingTitle);

        assertNull(notExistingBook);

        Mockito.verify(bookRepository, never()).save(any(BookModel.class));
    }

    @Test
    public void testNotExistingCategoryEqualsToNullAddToBook() {
        String title = "Title";
        String notExistingGenre = "NotExistingGenre";

        BookModel book = new BookModel();
        book.setTitle(title);
        book.setAuthor("Author");
        book.setAvailable(true);

        when(bookRepository.findByTitle(title)).thenReturn(book);
        when(categoryRepository.findByGenre(notExistingGenre)).thenReturn(null);

        BookModel notExistingGenreOfBook = bookTocategoryService.setBookGenre(notExistingGenre, title);

        assertNull(notExistingGenreOfBook);

        Mockito.verify(categoryRepository, never()).save(any(CategoryModel.class));
    }

    @Test
    public void testNotExistingCategoryAndBookEqualToNull() {
        String notExistingTitle = "NotExistingTitle";
        String notExistingGenre = "NotExistingGenre";

        when(bookRepository.findByTitle(notExistingTitle)).thenReturn(null);
        when(categoryRepository.findByGenre(notExistingGenre)).thenReturn(null);

        BookModel notExistingGenreAndBook = bookTocategoryService.setBookGenre(notExistingGenre, notExistingTitle);

        assertNull(notExistingGenreAndBook);

        Mockito.verify(bookRepository, never()).save(any(BookModel.class));
        Mockito.verify(categoryRepository, never()).save(any(CategoryModel.class));
    }


}
