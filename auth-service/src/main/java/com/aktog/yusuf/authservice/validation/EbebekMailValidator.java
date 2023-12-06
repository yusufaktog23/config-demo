package com.aktog.yusuf.authservice.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EbebekMailValidator implements ConstraintValidator<EbebekMail, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        final String ebebekMailDomain = "@ebebek.com";
        return s.contains(ebebekMailDomain);
    }

}
