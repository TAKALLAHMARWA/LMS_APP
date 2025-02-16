package com.example.userservice.dao.Entity;

import lombok.Getter;

@Getter
public enum Role {
    STUDENT("student"),
    TEACHER("teacher");
    //ADMIN("admin");

    private final String role;

    Role(String role) {
        this.role = role;
    }

    public static Role fromString(String role) {
        for (Role r : Role.values()) {
            if (r.getRole().equalsIgnoreCase(role)) {
                return r;
            }
        }
        throw new IllegalArgumentException("Invalid role: " + role);
    }
}
