package com.example.web.BackEnd.RestApi.controllers;

import com.example.web.BackEnd.RestApi.models.UserModel;
import com.example.web.BackEnd.RestApi.services.User_BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userBookOrder")
@CrossOrigin(origins = "http://localhost:63342")
public class User_BookController {

    @Autowired
    private User_BookService userBookService;

    @PostMapping("/{email}/{title}")
    public ResponseEntity<UserModel> userGetsBook(@PathVariable String email, @PathVariable String title) {
        UserModel user = userBookService.addBookToUser(email, title);

        if (user != null) {
            return ResponseEntity.ok(user);
        }

        return ResponseEntity.notFound().build();
    }

}
