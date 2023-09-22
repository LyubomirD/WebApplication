package com.example.web.BackEnd.RestApi.UserLogin;

import com.example.web.BackEnd.CustomAnnotations.PreventXSSAttacks;
import com.example.web.BackEnd.CustomAnnotations.ValidPasswordFormat;
import com.example.web.BackEnd.CustomAnnotations.ValidEmailFormat;
import com.example.web.BackEnd.RestApi.Tasks.TaskModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.scheduling.config.Task;

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
    private UUID userId;

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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<TaskModel> tasks;

}
