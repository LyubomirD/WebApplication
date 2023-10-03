//package com.example.web.BackEnd.RestApi.BorrowedBooks;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/borrowed-books")
//@CrossOrigin(origins = "http://localhost:63342")
//public class BorrowBookController {
//
//    @Autowired
//    private BorrowBookService borrowBookService;
//
//    @GetMapping("/listBooks")
//    public List<BorrowBookModel> getUserBorrowedBooks() {
//        return borrowBookService.listAllBooks();
//    }
//
//    @PostMapping("/borrow")
//    public ResponseEntity<BorrowBookModel> borrowBook(@RequestBody BorrowBookModel borrowBookModel) {
//        BorrowBookModel borrowedBook = borrowBookService.borrowBook(borrowBookModel);
//        return ResponseEntity.ok(borrowedBook);
//    }
//
////    @PutMapping("/return/{borrowId}")
////    public ResponseEntity<BorrowBookModel> returnBorrowedBook(@PathVariable int borrowId) {
////        BorrowBookModel returnedBook = borrowBookService.returnBorrowedBook(borrowId);
////        return ResponseEntity.ok(returnedBook);
////    }
//
//    //TODO Post borrowed book, put returned book, get see all books
//    // How would the connection, relation would work? Would I add and update manually to a table
//    // or this would be a some kind of junction table? I should think about it!
//
//}
