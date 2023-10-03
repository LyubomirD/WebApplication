//package com.example.web.BackEnd.RestApi.BorrowedBooks;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.crossstore.ChangeSetPersister;
//import org.springframework.stereotype.Service;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class BorrowBookService {
//
//    @Autowired
//    private BorrowBookRepository borrowBookRepository;
//
//    public List<BorrowBookModel> listAllBooks() {
//        return borrowBookRepository.findAll();
//    }
//
//    public BorrowBookModel borrowBook(BorrowBookModel borrowBookModel) {
//        return borrowBookRepository.save(borrowBookModel);
//    }
//
//}