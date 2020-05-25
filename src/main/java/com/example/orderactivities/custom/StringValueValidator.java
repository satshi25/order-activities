package com.example.orderactivities.custom;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.Objects;

public class StringValueValidator implements ConstraintValidator<StringValueConstraint, List<String>> {

    @Override
    public boolean isValid(List<String> value, ConstraintValidatorContext context) {
        if(!Objects.isNull(value) && value.size() == 2) {
            if(value.get(0) instanceof String || value.get(1) instanceof String) {
                return true;
            }
        }

        return false;
    }
}
