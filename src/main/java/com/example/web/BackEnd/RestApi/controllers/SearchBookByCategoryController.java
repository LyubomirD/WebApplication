package com.example.web.BackEnd.RestApi.controllers;


import com.example.web.BackEnd.RestApi.models.BookModel;
import com.example.web.BackEnd.RestApi.services.SearchBookByCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userSearchBooks")
@CrossOrigin(origins = "http://localhost:63342")
public class SearchBookByCategoryController {

    @Autowired
    private SearchBookByCategoryService searchBookByCategoryService;

    @GetMapping("/{genre}")
    public List<BookModel> getBooksByGenre(@PathVariable String genre) {
        return searchBookByCategoryService.findByGenre(genre);
    }

    @GetMapping("/{param1}/{param2}")
    public ResponseEntity<BookModel> getBookCategory(@PathVariable String param1, @PathVariable String param2) {
        BookModel book = searchBookByCategoryService.getBookByGenre(param1, param2);

        if (book == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(book);
    }
}