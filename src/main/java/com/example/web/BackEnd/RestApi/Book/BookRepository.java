package com.example.web.BackEnd.RestApi.Book;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<BookModel, Integer> {
    List<BookModel> findByAvailable(boolean available);

}
