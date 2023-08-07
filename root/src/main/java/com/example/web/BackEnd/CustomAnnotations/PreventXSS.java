package com.example.web.BackEnd.CustomAnnotations;

import com.example.web.BackEnd.RestApi.UserModel;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.commons.text.StringEscapeUtils;



public class PreventXSS implements ConstraintValidator<PreventXSSAttacks, UserModel> {

    @Override
    public void initialize(PreventXSSAttacks constraintAnnotation) {
    }

    @Override
    public boolean isValid(UserModel userModel, ConstraintValidatorContext context) {
        String username = userModel.getUsername();
        String email = userModel.getEmail();
        String password = userModel.getPassword();

        if (containsHarmfulContent(username) || containsHarmfulContent(email) || containsHarmfulContent(password)) {
            return false;
        }

        return true;
    }

    private boolean containsHarmfulContent(String input) {
        String sanitizedInput = StringEscapeUtils.escapeHtml4(input);

        return !input.equals(sanitizedInput) || input.contains("&");
    }
}
