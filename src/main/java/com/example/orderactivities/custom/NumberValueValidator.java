package com.example.orderactivities.custom;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberValueValidator implements ConstraintValidator<NumberValueConstraint, Integer> {
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
            Pattern customPattern = Pattern.compile("^([1-9]+(?:[0-9]*)?)$");
            Matcher numberMatcher = customPattern.matcher(String.valueOf(value));
        return numberMatcher.matches();
    }
}
