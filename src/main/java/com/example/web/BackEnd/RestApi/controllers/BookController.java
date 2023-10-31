package com.example.web.BackEnd.RestApi.controllers;

import com.example.web.BackEnd.RestApi.models.BookModel;
import com.example.web.BackEnd.RestApi.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/managerBooks")
@CrossOrigin(origins = "http://localhost:63342")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/new-book")
    public ResponseEntity<BookModel> addBook(@RequestBody BookModel bookModel) {
        BookModel newBook = bookService.addNewBook(bookModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(newBook);
    }

    @PutMapping("/update-book/{id}")
    public ResponseEntity<BookModel> updateBook(@PathVariable int id, @RequestBody BookModel bookModel) {
        BookModel updateBook = bookService.updateBook(id, bookModel);

        if (updateBook != null) {
            return ResponseEntity.ok(updateBook);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("delete-book/{available}")
    public void removeUnavailableBook(@PathVariable boolean available) {
        bookService.removeBook(available);
    }

}
