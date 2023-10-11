package com.example.web.BackendTest.RestApiTest.models;

import com.example.web.BackEnd.RestApi.models.CategoryModel;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = CategoryModelTest.class)
public class CategoryModelTest {

    @Test
    public void CategoryModelSaveInformation() {
        CategoryModel categoryModel = new CategoryModel();
        categoryModel.setGenre("Mystery");

        assertEquals("Mystery", categoryModel.getGenre());
    }




}
