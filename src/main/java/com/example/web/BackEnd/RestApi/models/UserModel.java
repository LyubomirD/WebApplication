package com.example.web.BackEnd.RestApi.models;

import com.example.web.BackEnd.CustomAnnotations.ValidPasswordFormat;
import com.example.web.BackEnd.CustomAnnotations.ValidEmailFormat;
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
    @Column(name = "username")
    private String username;

    @NotNull
    @ValidEmailFormat
    @Column(name = "email")
    private String email;

    @NotNull
    @ValidPasswordFormat
    @Column(name = "password")
    private String password;

    @OneToMany(fetch = FetchType.EAGER)
    private List<BookModel> books;

}
