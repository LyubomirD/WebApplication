package com.example.web.BackendTests.CustomAnnotations;

import com.example.web.BackEnd.CustomAnnotations.PreventXSS;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import javax.validation.ConstraintValidatorContext;
import static org.mockito.Mockito.mock;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

@SpringBootTest(classes = PreventXSSTest.class)
public class PreventXSSTest {
    private PreventXSS validator;
    private ConstraintValidatorContext context;

    @BeforeEach
    public void setUp() {
        validator = new PreventXSS();
        context = mock(ConstraintValidatorContext.class);
    }

    @Test
    public void testValidUsername() {
        String usernameValid = "valid-username";
        assertTrue(validator.isValid(usernameValid, context));
    }

    @Test
    public void testValidEmail() {
        String emailValid = "valid-email@example.com";
        assertTrue(validator.isValid(emailValid, context));
    }

    @Test
    public void testValidPassword() {
        String passwordValid = "valid-password";
        assertTrue(validator.isValid(passwordValid, context));
    }

    @Test
    public void testWithXSSInvalidValue() {
        String usernameInvalid = "<script>alert('XSS');</script>";
        assertFalse(validator.isValid(usernameInvalid, context));
    }

    @Test
    public void testWithAmpersandInvalidUsername() {
        String usernameAmpersandInvalid = "valid-username&";
        assertFalse(validator.isValid(usernameAmpersandInvalid, context));
    }


    @Test
    public void testWithAmpersandInvalidEmail() {
        String emailAmpersandInvalid = "valid-email@example.com&";
        assertFalse(validator.isValid(emailAmpersandInvalid, context));
    }


    @Test
    public void testWithAmpersandInvalidPassword() {
        String passwordAmpersandInvalid = "password&";
        assertFalse(validator.isValid(passwordAmpersandInvalid, context));
    }

}

