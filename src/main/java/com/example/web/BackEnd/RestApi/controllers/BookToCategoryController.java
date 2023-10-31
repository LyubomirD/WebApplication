package com.example.web.BackEnd.RestApi.controllers;

import com.example.web.BackEnd.RestApi.models.BookModel;
import com.example.web.BackEnd.RestApi.services.BookToCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/managerAddCategoriesToBooks")
@CrossOrigin(origins = "http://localhost:63342")
public class BookToCategoryController {

    @Autowired
    private BookToCategoryService bookCategoryService;

    @PostMapping("/{param1}/{param2}")
    public ResponseEntity<BookModel> setBookCategory(@PathVariable String param1, @PathVariable String param2) {
        BookModel book = bookCategoryService.setBookGenre(param1, param2);

        if (book == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(book);
    }
}
