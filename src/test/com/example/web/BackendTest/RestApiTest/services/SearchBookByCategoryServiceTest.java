package com.example.web.BackendTest.RestApiTest.services;

import com.example.web.BackEnd.RestApi.models.BookModel;
import com.example.web.BackEnd.RestApi.models.CategoryModel;
import com.example.web.BackEnd.RestApi.repositories.BookRepository;
import com.example.web.BackEnd.RestApi.repositories.CategoryRepository;
import com.example.web.BackEnd.RestApi.services.SearchBookByCategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = SearchBookByCategoryServiceTest.class)
public class SearchBookByCategoryServiceTest {

    @InjectMocks
    private SearchBookByCategoryService searchService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindBookByGenreWithValidCategory() {
        String genre = "Mystery";
        CategoryModel mockCategory = new CategoryModel();
        mockCategory.setGenre(genre);

        BookModel mockBook = new BookModel();
        mockBook.setTitle("Sample Book");
        mockBook.setCategories(Set.of(mockCategory));

        when(categoryRepository.findByGenre(genre)).thenReturn(mockCategory);
        when(bookRepository.findBookModelsByCategories(mockCategory)).thenReturn(List.of(mockBook));

        List<BookModel> bookList = searchService.findByGenre(genre);

        assertEquals(1, bookList.size());
        assertEquals("Sample Book", bookList.get(0).getTitle());
    }

    @Test
    public void testFindByGenreWithInvalidCategory() {
        String genre = "Invalid Genre";

        when(categoryRepository.findByGenre(genre)).thenReturn(null);

        List<BookModel> bookList = searchService.findByGenre(genre);

        assertEquals(0, bookList.size());
    }

    @Test
    public void testGetBookByGenreWithValidGenreAndTitle() {
        String genre = "Mystery";
        String title = "Sample Book";

        CategoryModel mockCategory = new CategoryModel();
        mockCategory.setGenre(genre);

        BookModel mockBook = new BookModel();
        mockBook.setTitle(title);

        when(categoryRepository.findByGenre(genre)).thenReturn(mockCategory);
        when(bookRepository.findByTitle(title)).thenReturn(mockBook);

        BookModel result = searchService.getBookByGenre(genre, title);

        assertEquals(title, result.getTitle());
    }

    @Test
    public void testGetBookByGenreWithInvalidGenre() {
        String genre = "Invalid Genre";
        String title = "Sample Book";

        when(categoryRepository.findByGenre(genre)).thenReturn(null);

        BookModel result = searchService.getBookByGenre(genre, title);

        assertEquals(null, result);
    }
}
