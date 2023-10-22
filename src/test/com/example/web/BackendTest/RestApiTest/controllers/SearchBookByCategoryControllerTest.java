package com.example.web.BackendTest.RestApiTest.controllers;

import com.example.web.BackEnd.RestApi.controllers.SearchBookByCategoryController;
import com.example.web.BackEnd.RestApi.models.BookModel;
import com.example.web.BackEnd.RestApi.models.CategoryModel;
import com.example.web.BackEnd.RestApi.services.SearchBookByCategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = SearchBookByCategoryControllerTest.class)
public class SearchBookByCategoryControllerTest {

    @InjectMocks
    private SearchBookByCategoryController searchController;

    @Mock
    private SearchBookByCategoryService searchService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSearchByGenreGetBookList() {
        String genre = "Criminal";

        CategoryModel mockCategory = new CategoryModel();
        mockCategory.setGenre(genre);

        BookModel mockBook = new BookModel();
        mockBook.setTitle("Sherlock Holmes");
        mockBook.setAuthor("Sir. Arthur");
        mockBook.setAvailable(true);
        mockBook.setCategories(Set.of(mockCategory));

        List<BookModel> listBooks = new ArrayList<>();
        listBooks.add(mockBook);

        when(searchService.findByGenre(genre)).thenReturn(listBooks);

        List<BookModel> result = searchController.getBooksByGenre(genre);

        assertNotNull(result);
        assertEquals(1, result.size());
        BookModel retrievedBook = result.get(0);
        assertEquals(mockBook.getTitle(), retrievedBook.getTitle());
        assertEquals(mockBook.getAuthor(), retrievedBook.getAuthor());
    }

    @Test
    public void testProvidingInvalidGenreToSearchForBookList() {
        String invalidGenre = "InvalidGenre";
        when(searchService.findByGenre(invalidGenre)).thenReturn(new ArrayList<>());

        List<BookModel> result = searchController.getBooksByGenre(invalidGenre);

        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    public void testProvidingInvalidNullGenreToSearchForBookList() {
        String nullGenre = null;
        when(searchService.findByGenre(nullGenre)).thenReturn(new ArrayList<>());

        List<BookModel> result = searchController.getBooksByGenre(nullGenre);

        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    public void testSearchByParametersBookTitleAndGenre() {
        String genre = "Mystery";
        String title = "The Da Vinci Code";

        CategoryModel mockCategory = new CategoryModel();
        mockCategory.setGenre(genre);

        BookModel mockBook = new BookModel();
        mockBook.setTitle(title);
        mockBook.setAuthor("Dan Brown");
        mockBook.setAvailable(true);
        mockBook.setCategories(Set.of(mockCategory));

        when(searchService.getBookByGenre(title, genre)).thenReturn(mockBook);

        ResponseEntity<BookModel> responseEntity = searchController.getBookCategory(title, genre);

        assertNotNull(responseEntity);
        verify(searchService).getBookByGenre(title, genre);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testSearchByParametersGenreAndBookTitle() {
        String genre = "Mystery";
        String title = "The Da Vinci Code";

        CategoryModel mockCategory = new CategoryModel();
        mockCategory.setGenre(genre);

        BookModel mockBook = new BookModel();
        mockBook.setTitle(title);
        mockBook.setAuthor("Dan Brown");
        mockBook.setAvailable(true);
        mockBook.setCategories(Set.of(mockCategory));

        when(searchService.getBookByGenre(genre, title)).thenReturn(mockBook);

        ResponseEntity<BookModel> responseEntity = searchController.getBookCategory(genre, title);

        assertNotNull(responseEntity);
        verify(searchService).getBookByGenre(genre, title);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testSearchByInvalidBookTitle() {
        String genre = "Genre";
        String title = "Not Existing Title";

        CategoryModel mockCategory = new CategoryModel();
        mockCategory.setGenre(genre);

        BookModel mockBook = new BookModel();
        mockBook.setCategories(Set.of(mockCategory));

        when(searchService.getBookByGenre(genre, title)).thenReturn(null);

        ResponseEntity<BookModel> responseEntity = searchController.getBookCategory(genre, title);

        assertNotNull(responseEntity);
        verify(searchService).getBookByGenre(genre, title);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void testSearchByInvalidGenre() {
        String genre = "Not Existing Genre";
        String title = "Title";

        CategoryModel mockCategory = new CategoryModel();

        BookModel mockBook = new BookModel();
        mockBook.setTitle(title);
        mockBook.setAuthor("Dan Brown");
        mockBook.setAvailable(true);
        mockBook.setCategories(Set.of(mockCategory));

        when(searchService.getBookByGenre(genre, title)).thenReturn(null);

        ResponseEntity<BookModel> responseEntity = searchController.getBookCategory(genre, title);

        assertNotNull(responseEntity);
        verify(searchService).getBookByGenre(genre, title);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void testSearchByParametersBookTitleNullAndGenre() {
        String genre = "Genre";
        String title = null;

        CategoryModel mockCategory = new CategoryModel();
        mockCategory.setGenre(genre);

        BookModel mockBook = new BookModel();
        mockBook.setTitle(title);
        mockBook.setAuthor("Author");
        mockBook.setAvailable(true);
        mockBook.setCategories(Set.of(mockCategory));

        when(searchService.getBookByGenre(genre, title)).thenReturn(null);

        ResponseEntity<BookModel> responseEntity = searchController.getBookCategory(genre, title);

        assertNotNull(responseEntity);
        verify(searchService).getBookByGenre(genre, title);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void testSearchByParametersBookTitleAndGenreNull() {
        String genre = null;
        String title = "Title";

        CategoryModel mockCategory = new CategoryModel();
        mockCategory.setGenre(genre);

        BookModel mockBook = new BookModel();
        mockBook.setTitle(title);
        mockBook.setAuthor("Author");
        mockBook.setAvailable(true);
        mockBook.setCategories(Set.of(mockCategory));

        when(searchService.getBookByGenre(genre, title)).thenReturn(null);

        ResponseEntity<BookModel> responseEntity = searchController.getBookCategory(genre, title);

        assertNotNull(responseEntity);
        verify(searchService).getBookByGenre(genre, title);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void testSearchByParametersBothNull() {
        String genre = null;
        String title = null;

        when(searchService.getBookByGenre(genre, title)).thenReturn(null);

        ResponseEntity<BookModel> responseEntity = searchController.getBookCategory(genre, title);

        assertNotNull(responseEntity);
        verify(searchService).getBookByGenre(genre, title);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
}
