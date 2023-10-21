package com.example.web.BackEnd.RestApi.services;

import com.example.web.BackEnd.RestApi.models.BookModel;
import com.example.web.BackEnd.RestApi.models.CategoryModel;
import com.example.web.BackEnd.RestApi.repositories.BookRepository;
import com.example.web.BackEnd.RestApi.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookToCategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BookRepository bookRepository;


    public BookModel setBookGenre(String genre, String title) {
        CategoryModel category = categoryRepository.findByGenre(genre);
        BookModel book = bookRepository.findByTitle(title);

        if (category == null || book == null) {
            return null;
        }

        book.getCategories().add(category);
        bookRepository.save(book);
        return book;
    }

}
