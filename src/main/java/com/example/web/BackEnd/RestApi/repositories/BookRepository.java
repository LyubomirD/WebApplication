package com.example.web.BackEnd.RestApi.repositories;

import com.example.web.BackEnd.RestApi.models.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookModel, Integer> {
    List<BookModel> findByAvailable(boolean available);

    BookModel findByTitle(String title);

    BookModel findByTitleAndAuthor(String title, String author);
}
