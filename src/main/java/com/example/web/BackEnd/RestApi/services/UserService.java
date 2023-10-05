package com.example.web.BackEnd.RestApi.services;

import com.example.web.BackEnd.RestApi.models.UserModel;
import com.example.web.BackEnd.RestApi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserModel getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public UserModel addNewUser(UserModel userModel) {
        UserModel existingEmail = userRepository.findByEmail(userModel.getEmail());

        if (existingEmail != null) {
            return null;
        }

        userModel.setEmail(HtmlUtils.htmlEscape(userModel.getEmail()));

        String hashedPassword = passwordEncoder.encode(userModel.getPassword());
        userModel.setPassword(hashedPassword);

        return userRepository.save(userModel);
    }

}