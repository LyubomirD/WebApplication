package com.example.web.BackEnd.Security.BCryptUserPassword;

import com.example.web.BackEnd.CustomException.EmailNotFoundException;
import com.example.web.BackEnd.RestApi.User.UserModel;
import com.example.web.BackEnd.RestApi.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class UserModelDetailsServiceImpl implements UserModelDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws EmailNotFoundException {
        UserModel user = userRepository.findByEmail(email);

        if (user == null) {
            throw new EmailNotFoundException("User email not found");
        }

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                new ArrayList<>()
        );
    }
}
