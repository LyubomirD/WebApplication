package com.example.web.BackEnd.CustomAnnotations;

import com.example.web.BackEnd.CustomException.ValidationException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailFormatValidator implements ConstraintValidator<ValidEmailFormat, String> {

    @Override
    public void initialize(ValidEmailFormat constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            throw new ValidationException("You have not provided an email address");
        }

        if (!value.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.+[A-Za-z0-9.-]+")) {
            throw new ValidationException("Incorrectly written email");
        }

        return true;
    }

}
