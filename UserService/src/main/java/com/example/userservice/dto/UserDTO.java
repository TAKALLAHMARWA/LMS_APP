package com.example.userservice.dto;

import com.example.userservice.dao.Entity.Role;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDTO {
    private String username;
    private Role role;
    private String instanceName;
}

