package com.example.web.BackEnd.RestApi.UserLogin;

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

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserModel> getAllUsers() {
        return userService.listAllUsers();
    }

    @GetMapping("/login")
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
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

}
