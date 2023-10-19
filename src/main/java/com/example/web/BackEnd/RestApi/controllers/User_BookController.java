package com.example.web.BackEnd.RestApi.controllers;

import com.example.web.BackEnd.RestApi.models.UserModel;
import com.example.web.BackEnd.RestApi.services.UserService;
import com.example.web.BackEnd.RestApi.services.User_BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usersRegistration/login")
@CrossOrigin(origins = "http://localhost:63342")
public class User_BookController {

    @Autowired
    private User_BookService userBookService;

    @PostMapping("/userBookOrder/{title}")
    public ResponseEntity<?> addUserBook(@PathVariable String title) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = userDetails.getUsername();
        UserModel user = userBookService.addBookToUser(email, title);

        return ResponseEntity.ok(user);
    }
}
