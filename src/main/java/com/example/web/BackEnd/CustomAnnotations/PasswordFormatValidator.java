package com.example.web.BackEnd.CustomAnnotations;

import com.example.web.BackEnd.CustomException.ValidationException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordFormatValidator implements ConstraintValidator<ValidPasswordFormat, String> {

    @Override
    public void initialize(ValidPasswordFormat constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            throw new ValidationException("You have not provided a password");
        }

        if (!value.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")) {
            throw new ValidationException("Password needs to have 8 characters, 1 uppercase letter, 1 lowercase letter, 1 number, 1 special symbol");
        }

        return true;
    }
}
