package com.example.web.BackEnd.RestApi.controllers;

import com.example.web.BackEnd.RestApi.models.BookModel;
import com.example.web.BackEnd.RestApi.services.Book_CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookGenre")
@CrossOrigin(origins = "http://localhost:63342")
public class Book_CategoryController {

    @Autowired
    private Book_CategoryService bookCategoryService;

    //TODO If I search only genre I would be routed by the categoryController if I add a title by this one
    @PostMapping("/{genre}/{title}")
    public ResponseEntity<BookModel> setBookCategory(@PathVariable String genre, @PathVariable String title) {
        BookModel book = bookCategoryService.setBookGenre(genre, title);

        if (book == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(book);
    }
}
