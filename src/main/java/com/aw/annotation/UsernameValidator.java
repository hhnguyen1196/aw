package com.aw.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;

public class UsernameValidator implements ConstraintValidator<Username, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        if (!StringUtils.hasText(s)) {
            context.buildConstraintViolationWithTemplate("Username không được để trống").addConstraintViolation();
            return false;
        }
        return true;
    }
}
