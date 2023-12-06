package com.aktog.yusuf.authservice.model.enumation;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    ROLE_USER("USER"),
    ROLE_ENGINEER("ENGINEER"),
    ROLE_ADMIN("ADMIN");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String getAuthority() {
        return this.getRole();
    }
}
