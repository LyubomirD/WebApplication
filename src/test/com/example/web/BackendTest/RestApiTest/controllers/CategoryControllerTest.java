package com.example.web.BackendTest.RestApiTest.controllers;

import com.example.web.BackEnd.RestApi.controllers.CategoryController;
import com.example.web.BackEnd.RestApi.models.BookModel;
import com.example.web.BackEnd.RestApi.models.CategoryModel;
import com.example.web.BackEnd.RestApi.services.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = CategoryControllerTest.class)
public class CategoryControllerTest {

    @InjectMocks
    private CategoryController categoryController;

    @Mock
    private CategoryService categoryService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddGenre() {
        CategoryModel mockCategory = new CategoryModel();
        mockCategory.setGenre("Example");

        when(categoryService.setNewCategory(mockCategory)).thenReturn(mockCategory);

        ResponseEntity<CategoryModel> responseEntity = categoryController.setCategories(mockCategory);

        verify(categoryService).setNewCategory(mockCategory);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(mockCategory, responseEntity.getBody());
    }
}
