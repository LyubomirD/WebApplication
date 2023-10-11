package com.example.web.BackendTest.RestApiTest.services;

import com.example.web.BackEnd.RestApi.models.CategoryModel;
import com.example.web.BackEnd.RestApi.repositories.CategoryRepository;
import com.example.web.BackEnd.RestApi.services.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.aspectj.bridge.MessageUtil.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = CategoryServiceTest.class)
public class CategoryServiceTest {

    @InjectMocks
    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSetNewCategory() {
        CategoryModel categoryModel = new CategoryModel();
        categoryModel.setGenre("Romance");

        Mockito.when(categoryRepository.save(categoryModel)).thenReturn(categoryModel);

        CategoryModel result = categoryService.setNewCategory(categoryModel);

        // Verify that the result is the same object
        assertEquals(categoryModel, result);
    }

    @Test
    public void testAddGenreNullValue() {
        CategoryModel categoryModel = new CategoryModel();
        categoryModel.setGenre(null);

        try {
            categoryService.setNewCategory(categoryModel);
        } catch (IllegalArgumentException e) {
            assertEquals("Genre cannot be null", e.getMessage());
        }

        Mockito.verify(categoryRepository, Mockito.never()).save(Mockito.any());
    }

    @Test
    public void testAddAlreadyExistingGenre() {
        CategoryModel existingCategory = new CategoryModel();
        existingCategory.setGenre("Self Improvement");

        CategoryModel notUnique = new CategoryModel();
        notUnique.setGenre("Self Improvement");

        when(categoryRepository.findByGenre(existingCategory.getGenre())).thenReturn(existingCategory);

        try {
            categoryService.setNewCategory(notUnique);
            fail("Expected an exception to be thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("Genre 'Self Improvement' already exists", e.getMessage());
        }

        Mockito.verify(categoryRepository, Mockito.never()).save(Mockito.any());
    }


}

