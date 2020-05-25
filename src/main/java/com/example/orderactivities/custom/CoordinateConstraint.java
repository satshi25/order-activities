package com.example.orderactivities.custom;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Constraint(validatedBy = CoordinateValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface CoordinateConstraint {
    String message() default "The values are not valid coordinates.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
