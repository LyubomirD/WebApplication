package com.example.web.BackEnd.RestApi.controllers;

import com.example.web.BackEnd.RestApi.models.CategoryModel;
import com.example.web.BackEnd.RestApi.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categorySelection")
@CrossOrigin(origins = "http://localhost:63342")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/category")
    public ResponseEntity<CategoryModel> setCategories(@RequestBody CategoryModel categoryModel) {
        CategoryModel category = categoryService.setNewCategory(categoryModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }

}
