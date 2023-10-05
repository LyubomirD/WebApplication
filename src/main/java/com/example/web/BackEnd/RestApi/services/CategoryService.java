package com.example.web.BackEnd.RestApi.services;

import com.example.web.BackEnd.RestApi.models.CategoryModel;
import com.example.web.BackEnd.RestApi.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryModel setNewCategory(CategoryModel categoryModel) {
        return categoryRepository.save(categoryModel);
    }
}
