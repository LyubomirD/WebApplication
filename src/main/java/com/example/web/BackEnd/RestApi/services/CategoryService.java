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
        if (categoryModel.getGenre() == null) {
            throw new IllegalArgumentException("Genre cannot be null");
        }

        CategoryModel existingCategory = categoryRepository.findByGenre(categoryModel.getGenre());
        if (existingCategory != null) {
            throw new IllegalArgumentException("Genre '" + categoryModel.getGenre() + "' already exists");
        }

        return categoryRepository.save(categoryModel);
    }
}
