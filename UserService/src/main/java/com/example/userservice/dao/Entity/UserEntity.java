package com.example.userservice.dao.Entity;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@MappedSuperclass
public abstract class UserEntity {
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role; // Utilisation de l'Enum Role
    private String instanceName;
}
