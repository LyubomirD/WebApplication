package com.example.web.BackEnd.RestApi.services;

import com.example.web.BackEnd.RestApi.models.BookModel;
import com.example.web.BackEnd.RestApi.models.CategoryModel;
import com.example.web.BackEnd.RestApi.repositories.BookRepository;
import com.example.web.BackEnd.RestApi.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchBookByCategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BookRepository bookRepository;

    public boolean doesGenreExist(String genre) {
        CategoryModel category = categoryRepository.findByGenre(genre);

        if (category == null) {
            return false;
        }

        return true;
    }

    public BookModel getBookByGenre(String genre, String title) {
        if (!doesGenreExist(genre)) {
            return null;
        }

        BookModel book = bookRepository.findByTitle(title);

        return book;
    }

    public List<BookModel> findByGenre(String genre) {
        return (List<BookModel>) categoryRepository.findByGenre(genre);
    }
}
