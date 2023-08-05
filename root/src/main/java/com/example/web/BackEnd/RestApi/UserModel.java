package com.example.web.BackEnd.RestApi;

import com.example.web.CustomAnnotations.ValidEmailFormat;
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
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userid")
    private UUID userId;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "usernumber")
    private int userNumber;

    @NotNull
    @Column(name = "username")
    private String username;

    @NotNull
    @ValidEmailFormat
    @Column(name = "email")
    private String email;

    @NotNull
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
            message = "Password must contain at least 8 characters, one digit, one lowercase letter, one uppercase letter, and one special character")
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
