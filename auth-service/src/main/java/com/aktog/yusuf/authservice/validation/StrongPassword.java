package com.aktog.yusuf.authservice.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = StrongPasswordValidator.class)
@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface StrongPassword {
    String message() default "Password should consists of at least." +
            " One special character, one uppercase & one lowercase and one digit";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
