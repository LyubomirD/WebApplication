package com.example.web.BackEnd.RestApi.services;

import com.example.web.BackEnd.RestApi.models.BookModel;
import com.example.web.BackEnd.RestApi.models.CategoryModel;
import com.example.web.BackEnd.RestApi.models.UserModel;
import com.example.web.BackEnd.RestApi.repositories.BookRepository;
import com.example.web.BackEnd.RestApi.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class SearchBookByCategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BookRepository bookRepository;

    public List<BookModel> findByGenre(String genre) {
        CategoryModel category = categoryRepository.findByGenre(genre);

        if (category == null) {
            return Collections.emptyList();
        }

        return bookRepository.findBookModelsByCategories(category);
    }

    public BookModel getBookByGenre(String param1, String param2) {
        String genre, title;

        if (doesGenreExist(param1)) {
            genre = param1;
            title = param2;
        } else {
            genre = param2;
            title = param1;
        }

        if (!doesGenreExist(genre)) {
            return null;
        }

        return bookRepository.findByTitle(title);
    }

    private boolean doesGenreExist(String genre) {
        CategoryModel category = categoryRepository.findByGenre(genre);

        if (category == null) {
            return false;
        }

        return true;
    }
}
