package com.example.web.BackEnd.RestApi;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Integer> {
    UserModel findByEmailAndPassword(String email, String password);
}
