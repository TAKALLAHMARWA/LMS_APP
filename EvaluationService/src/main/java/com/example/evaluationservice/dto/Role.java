package com.example.evaluationservice.dto;

import lombok.Getter;

@Getter
public enum Role {
    STUDENT("student"),
    TEACHER("teacher");
    private final String role;

    Role(String role) {
        this.role = role;
    }
}