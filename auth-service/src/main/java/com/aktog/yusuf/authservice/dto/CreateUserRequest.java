package com.aktog.yusuf.authservice.dto;


import com.aktog.yusuf.authservice.model.enumation.UserRole;
import com.aktog.yusuf.authservice.validation.EbebekMail;
import com.aktog.yusuf.authservice.validation.StrongPassword;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Set;


public record CreateUserRequest(
        @NotBlank
        String name,
        @Size(min = 5, max = 64)
        String username,
        @Size(min = 6, max = 32)
        @StrongPassword
        String password,

        @EbebekMail
        String mail,
        @NotEmpty(message = "It has to be given at least one role...")
        Set<UserRole> roles) {


}
