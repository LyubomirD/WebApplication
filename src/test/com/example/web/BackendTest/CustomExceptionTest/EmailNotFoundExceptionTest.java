package com.example.web.BackendTest.CustomExceptionTest;

import com.example.web.BackEnd.CustomException.EmailNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = EmailNotFoundExceptionTest.class)
public class EmailNotFoundExceptionTest {

    @Test
    public void testEmailNotFoundException() {
        assertThrows(EmailNotFoundException.class, () -> {
            throw new EmailNotFoundException("Email not found");
        });
    }

}
