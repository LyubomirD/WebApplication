package com.example.web.BackEnd.RestApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private List<UserModel> listAllUsers() {
        return userRepository.findAll();
    }

    private UserModel getUserOneByEmail(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    private UserModel createNewUser(UserModel userModel) {
        return userRepository.save(userModel);
    }

    private UserModel updateExistingUser(int userNumber, UserModel userModel) {
        UserModel existingUser = userRepository.findById(userNumber).orElseThrow();

        existingUser.setUsername(userModel.getUsername());
        existingUser.setPassword(userModel.getPassword());
        return userRepository.save(existingUser);
    }

    private boolean deleteOneUserById(int userNumber) {
        if (userRepository.existsById(userNumber)) {
            userRepository.deleteById(userNumber);
            return true;
        }
        return false;
    }

    public List<UserModel> getAllUsers() {
        return listAllUsers();
    }

    public UserModel getUserByEmail(String email, String password) {
        return getUserOneByEmail(email, password);
    }

    public UserModel createUser(UserModel userModel) {
        return createNewUser(userModel);
    }

    public UserModel updateUser(int userNumber, UserModel userModel) {
        return updateExistingUser(userNumber, userModel);
    }

    public boolean deleteUser(int userNumber) {
        return deleteOneUserById(userNumber);
    }

}
