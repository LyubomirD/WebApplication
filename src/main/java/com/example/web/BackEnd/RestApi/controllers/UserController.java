package com.example.web.BackEnd.RestApi.controllers;

import com.example.web.BackEnd.RestApi.models.UserModel;
import com.example.web.BackEnd.RestApi.services.UserService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/usersRegistration")
@CrossOrigin(origins = "http://localhost:63342")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<UserModel> getUser() {
        String authenticatedUsername = org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getName();
        UserModel searchedUser = userService.getUserByEmail(authenticatedUsername);

        if (searchedUser != null) {
            return ResponseEntity.ok(searchedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/sign-up")
    public ResponseEntity<UserModel> createUser(@Valid @RequestBody UserModel userModel, BindingResult bindingResult) {
        UserModel createdUser = userService.addNewUser(userModel);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(createdUser);
        }
        if (createdUser == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }
}
