package com.aktog.yusuf.authservice.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class StrongPasswordValidator implements ConstraintValidator<StrongPassword, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return hasAtLeastOneLowercaseLetter(s) && hasAtLeastOneUppercaseLetter(s)
                && hasAtLeastOneDigit(s) && hasAtLeastOneSpecialLetter(s);
    }

    private boolean hasAtLeastOneUppercaseLetter(String password) {
        return password.chars().mapToObj(p -> (char) p).anyMatch(Character::isUpperCase);
    }

    private boolean hasAtLeastOneLowercaseLetter(String password) {
        return password.chars().mapToObj(p -> (char) p).anyMatch(Character::isLowerCase);
    }

    private boolean hasAtLeastOneDigit(String password) {
        return password.chars().mapToObj(p -> (char) p).anyMatch(Character::isDigit);
    }

    private boolean hasAtLeastOneSpecialLetter(String password) {
        return password.chars().mapToObj(p -> (char) p).anyMatch(ch -> !(Character.isLetter(ch) || Character.isDigit(ch)));
    }

}
