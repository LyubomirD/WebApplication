package com.example.web.BackEnd.RestApi.controllers;

import com.example.web.BackEnd.RestApi.models.BookModel;
import com.example.web.BackEnd.RestApi.services.BookToCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/addGenreToBook")
@CrossOrigin(origins = "http://localhost:63342")
public class BookToCategoryController {

    @Autowired
    private BookToCategoryService bookCategoryService;

    @PostMapping("/{genre}/{title}")
    public ResponseEntity<BookModel> setBookCategory(@PathVariable String genre, @PathVariable String title) {
        BookModel book = bookCategoryService.setBookGenre(genre, title);

        if (book == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(book);
    }
}
