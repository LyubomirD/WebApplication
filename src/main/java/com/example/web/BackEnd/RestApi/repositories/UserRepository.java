package com.example.web.BackEnd.RestApi.repositories;

import com.example.web.BackEnd.RestApi.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Integer> {
    UserModel findByEmail(String email);

}
