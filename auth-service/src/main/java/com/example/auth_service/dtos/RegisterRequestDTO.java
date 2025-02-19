package com.example.auth_service.dtos;

import com.example.auth_service.dao.entities.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequestDTO {
    private String username;
    private String password;
    private Role role;
}
