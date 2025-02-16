package com.example.userservice.dao.Entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor


public abstract class UserEntity {

    @Id
    private Long id;
    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role; // Utilisation de l'Enum Role


}
