package com.example.web.BackEnd.RestApi;

import com.example.web.BackEnd.CustomAnnotations.PreventXSSAttacks;
import com.example.web.BackEnd.CustomAnnotations.ValidEmailFormat;
import com.example.web.BackEnd.CustomAnnotations.ValidPasswordFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@PreventXSSAttacks
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userid")
    private UUID userId;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "usernumber")
    private int userNumber;

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

    public UserModel() {

    }
    public UserModel(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

}
