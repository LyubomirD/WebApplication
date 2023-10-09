package com.example.web.BackEnd.RestApi.services;

import com.example.web.BackEnd.CustomException.DuplicateBookException;
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
        String title = bookModel.getTitle();
        String author = bookModel.getAuthor();

        BookModel existingBook = bookRepository.findByTitleAndAuthor(title, author);

        if (existingBook != null) {
            throw new DuplicateBookException("A book with the same title and author already exists.");
        }

        return bookRepository.save(bookModel);
    }

    public BookModel updateBook(int id, BookModel bookModel) {
        BookModel existingBook = bookRepository.findById(id).orElse(null);

        if (existingBook == null) {
            return null;
        }

        existingBook.setAvailable(bookModel.isAvailable());

        return bookRepository.save(existingBook);
    }

    public void removeBook(boolean available) {
        List<BookModel> bookAvailability = bookRepository.findByAvailable(available);
        bookRepository.deleteAll(bookAvailability);
    }
}
