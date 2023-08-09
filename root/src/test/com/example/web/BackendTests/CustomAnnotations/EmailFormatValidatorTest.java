package com.example.web.BackendTests.CustomAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import javax.validation.ConstraintValidatorContext;

import com.example.web.BackEnd.CustomAnnotations.EmailFormatValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = EmailFormatValidatorTest.class)
public class EmailFormatValidatorTest {

    private EmailFormatValidator validator;
    private ConstraintValidatorContext context;

    @BeforeEach
    public void setUp() {
        validator = new EmailFormatValidator();
        context = mock(ConstraintValidatorContext.class);
    }

    @Test
    public void testValidEmail() {
        assertTrue(validator.isValid("test@example.com", context));
    }

    @Test
    public void testInvalidEmail() {
        assertFalse(validator.isValid("invalid-email", context));
    }

    @Test
    public void testNullEmail() {
        assertFalse(validator.isValid(null, context));
    }

    @Test
    public void testEmptyEmail() {
        assertFalse(validator.isValid("", context));
    }
}
