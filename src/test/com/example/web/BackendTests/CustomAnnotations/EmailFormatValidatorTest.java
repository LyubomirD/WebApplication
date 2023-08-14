package com.example.web.BackendTests.CustomAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ValidationException;
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
        ValidationException exception = assertThrows(ValidationException.class, () -> {
            validator.isValid("invalid-email", context);
        });

        assertTrue(exception.getMessage().contains("Incorrectly written email"));
    }

    @Test
    public void testNullEmail() {
        ValidationException exception = assertThrows(ValidationException.class, () -> {
            validator.isValid(null, context);
        });

        assertFalse(exception.getMessage().contains("Incorrectly written email"));
    }

    @Test
    public void testEmptyEmail() {
        ValidationException exception = assertThrows(ValidationException.class, () -> {
            validator.isValid("", context);
        });

        assertFalse(exception.getMessage().contains("Incorrectly written email"));
    }
}
