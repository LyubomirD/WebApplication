package com.example.web.BackEnd.RestApi.services;

import com.example.web.BackEnd.RestApi.models.BookModel;
import com.example.web.BackEnd.RestApi.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public BookModel addNewBook(BookModel bookModel) {
        return bookRepository.save(bookModel);
    }

    public BookModel updateBook(int id, BookModel bookModel) {
        BookModel existingBook = bookRepository.findById(id).orElse(null);

        if (existingBook == null) {
            return null;
        }

        existingBook.setTitle(bookModel.getTitle());
        existingBook.setAuthor(bookModel.getAuthor());
        existingBook.setAvailable(bookModel.isAvailable());

        return bookRepository.save(existingBook);
    }

    public void removeBook(boolean available) {
        List<BookModel> bookAvailability = bookRepository.findByAvailable(available);
        bookRepository.deleteAll(bookAvailability);
    }
}
