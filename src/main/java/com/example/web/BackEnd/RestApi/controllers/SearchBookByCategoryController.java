package com.example.web.BackEnd.RestApi.controllers;


import com.example.web.BackEnd.RestApi.models.BookModel;
import com.example.web.BackEnd.RestApi.services.Book_CategoryService;
import com.example.web.BackEnd.RestApi.services.SearchBookByCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/searchGenreBook")
@CrossOrigin(origins = "http://localhost:63342")
public class SearchBookByCategoryController {

    @Autowired
    private SearchBookByCategoryService searchBookByCategoryService;


    // TODO finish the List that will show all the books from a certain genre -->
    @GetMapping("/{genre}")
    public List<BookModel> getBooksByGenre(String genre) {
        List<BookModel> books = searchBookByCategoryService.findByGenre(genre);

        return books;
    }

    @GetMapping("/{param1}/{param2}")
    public ResponseEntity<BookModel> getBookCategory(@PathVariable String param1, @PathVariable String param2) {
        String genre, title;

        if (isGenre(param1)) {
            genre = param1;
            title = param2;
        } else {
            genre = param2;
            title = param1;
        }

        BookModel book = searchBookByCategoryService.getBookByGenre(genre, title);

        if (book == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(book);
    }

    private boolean isGenre(String param1) {
        return searchBookByCategoryService.doesGenreExist(param1);
    }
}