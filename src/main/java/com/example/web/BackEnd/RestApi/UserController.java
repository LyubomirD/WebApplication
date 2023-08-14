package com.example.web.BackEnd.RestApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "https://lyubomird.github.io")
@Validated
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserModel> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/get/{email}/{password}")
    public ResponseEntity<UserModel> getUser(@Valid @PathVariable String email ,@Valid @PathVariable String password) {
        UserModel user = userService.getUserByEmailAndPassword(email, password);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/post")
    public ResponseEntity<UserModel> createUser(@Valid @RequestBody UserModel userModel, BindingResult bindingResult) {
        UserModel createdUser = userService.createUser(userModel);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(createdUser);
        }
        if (createdUser == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }


    // those methods are not currently in use
    @PutMapping("/update/{userNumber}")
    public ResponseEntity<UserModel> updateUser(@PathVariable int userNumber, @RequestBody UserModel userModel) {
        UserModel updatedUser = userService.updateUser(userNumber, userModel);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{userNumber}")
    public ResponseEntity<Void> deleteUser(@PathVariable int userNumber) {

        boolean deleted = userService.deleteUser(userNumber);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}