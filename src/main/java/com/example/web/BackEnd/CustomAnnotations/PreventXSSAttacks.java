package com.example.web.BackEnd.CustomAnnotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PreventXSS.class)
public @interface PreventXSSAttacks {
        String message() default "XSS attack detected";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};
}
