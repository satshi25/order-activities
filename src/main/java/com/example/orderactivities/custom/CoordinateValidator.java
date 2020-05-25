package com.example.orderactivities.custom;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CoordinateValidator implements
        ConstraintValidator<CoordinateConstraint, List<String>> {

    @Override
    public boolean isValid(List<String> value, ConstraintValidatorContext context) {
        boolean valid = false;
        if(!Objects.isNull(value) && value.size() == 2) {
            Pattern latPattern = Pattern.compile("^[-+]?([1-8]?\\d(\\.\\d+)?|90(\\.0+)?)$");
            Pattern longPattern = Pattern.compile("^[-+]?(180(\\.0+)?|((1[0-7]\\d)|([1-9]?\\d))(\\.\\d+)?)$");
            Matcher latMatcher = latPattern.matcher(value.get(0));
            Matcher longMatcher = longPattern.matcher(value.get(1));

            boolean validLat = latMatcher.matches();
            boolean validLong = longMatcher.matches();
            if (validLat == true && validLong == true) {
                valid = true;
            }
        }
        return valid;
    }
}
