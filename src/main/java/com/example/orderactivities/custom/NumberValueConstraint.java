package com.example.orderactivities.custom;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Constraint(validatedBy = NumberValueValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface NumberValueConstraint {
    String message() default "Not a valid integer parameter.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
