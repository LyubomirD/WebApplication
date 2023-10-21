package com.example.web.BackEnd.RestApi.services;

import com.example.web.BackEnd.RestApi.models.BookModel;
import com.example.web.BackEnd.RestApi.models.UserModel;
import com.example.web.BackEnd.RestApi.repositories.BookRepository;
import com.example.web.BackEnd.RestApi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserToBookService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    public UserModel addBookToUser(String email, String title) {
        UserModel user = userRepository.findByEmail(email);
        BookModel book = bookRepository.findByTitle(title);

        if (user == null || book == null) {
            return null;
        }

        user.getBooks().add(book);
        userRepository.save(user);
        return user;
    }
    @Transactional
    public UserModel removeBookFromUser(String email, String title) {
        UserModel user = userRepository.findByEmail(email);
        BookModel book = bookRepository.findByTitle(title);

        if (user == null) {
            return null;
        }

        if (book == null) {
            return null;
        }

        user.getBooks().remove(book);
        userRepository.save(user);
        return user;
    }
}
