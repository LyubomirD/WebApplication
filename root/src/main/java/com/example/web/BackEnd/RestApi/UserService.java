package com.example.web.BackEnd.RestApi;

import com.example.web.BackEnd.CustomAnnotations.ValidEmailFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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

    private UserModel getUserEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    private UserModel createNewUser(UserModel userModel) {
        UserModel existingEmail = userRepository.findByEmail(userModel.getEmail());

        if (existingEmail != null) {
            return null;
        }

//        if (isValidUser(userModel)) {
//            System.out.println("Validation method Server worked");
//            return null;
//        }

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

    public UserModel getUserByEmailAndPassword(String email, String password) {
        return getUserEmailAndPassword(email, password);
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

    private static boolean isValidUser(UserModel userModel) {

        String email = userModel.getEmail();
        String password = userModel.getPassword();

        if (email != null && email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            return false;
        }

        if (password != null && password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")) {
            return false;
        }

        return true;
    }

}
