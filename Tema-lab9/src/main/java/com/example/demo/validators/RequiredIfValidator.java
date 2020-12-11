package com.example.demo.validators;



import com.example.demo.dto.OwnerDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RequiredIfValidator implements ConstraintValidator<RequiredIf, OwnerDto> {

    @Override
    public boolean isValid(OwnerDto value, ConstraintValidatorContext context) {
        if (value.getHasShop()) {
            return value.getHasShop() != null;
        }
        return true;
    }
}
