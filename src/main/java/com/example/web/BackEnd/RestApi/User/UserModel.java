package com.example.web.BackEnd.RestApi.User;

import com.example.web.BackEnd.CustomAnnotations.PreventXSSAttacks;
import com.example.web.BackEnd.CustomAnnotations.ValidPasswordFormat;
import com.example.web.BackEnd.CustomAnnotations.ValidEmailFormat;
import com.example.web.BackEnd.RestApi.Book.BookModel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@RequiredArgsConstructor
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userid")
    private UUID userid;

    @NotNull
    @PreventXSSAttacks
    @Column(name = "username")
    private String username;

    @NotNull
    @ValidEmailFormat
    @PreventXSSAttacks
    @Column(name = "email")
    private String email;

    @NotNull
    @ValidPasswordFormat
    @PreventXSSAttacks
    @Column(name = "password")
    private String password;

    @OneToMany(fetch = FetchType.EAGER)
    private List<BookModel> books;

}
// TODO new controller 2 parameters ( userID, bookID), find both in DB, add book to user list, save user
