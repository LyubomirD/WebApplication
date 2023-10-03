//package com.example.web.BackEnd.RestApi.BorrowedBooks;
//
//import com.example.web.BackEnd.RestApi.User.UserModel;
//import lombok.Getter;
//import lombok.RequiredArgsConstructor;
//import lombok.Setter;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//
//@Entity
//@Table(name = "borrowbooks")
//@Setter
//@Getter
//@RequiredArgsConstructor
//public class BorrowBookModel {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "borrowid")
//    private int borrowid;
//
//    @Column(name = "borrowdate")
//    private LocalDateTime borrowDate;
//
//    @Column(name = "returndate")
//    private LocalDateTime returnDate;
//
//    // TODO many to many relationship
//}
