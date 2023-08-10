package com.example.web.BackEnd.CustomException;

import javax.validation.ConstraintViolationException;

public class ValidationException extends ConstraintViolationException {

    public ValidationException(String message) {
        super(message, null);
    }
}
