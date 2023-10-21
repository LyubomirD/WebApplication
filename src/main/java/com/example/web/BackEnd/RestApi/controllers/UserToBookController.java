package com.example.web.BackEnd.RestApi.controllers;

import com.example.web.BackEnd.RestApi.models.UserModel;
import com.example.web.BackEnd.RestApi.services.UserToBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usersRegistration/login")
@CrossOrigin(origins = "http://localhost:63342")
public class UserToBookController {

    @Autowired
    private UserToBookService userBookService;

    @PostMapping("/userBookOrder/{title}")
    public ResponseEntity<UserModel> addBookToUserList(@PathVariable String title) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = userDetails.getUsername();
        UserModel user = userBookService.addBookToUser(email, title);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(user);
        }

        return ResponseEntity.ok(user);
    }

    @PutMapping("/userBookOrder/{title}")
    public ResponseEntity<UserModel> removeBookFromUserList(@PathVariable String title) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = userDetails.getUsername();
        UserModel user = userBookService.removeBookFromUser(email, title);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(user);
        }

        return ResponseEntity.ok(user);
    }

}
