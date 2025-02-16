package com.example.userservice.mapper;


import com.example.userservice.dao.Entity.UserEntity;
import com.example.userservice.dto.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO toDTO(UserEntity entity) {
        UserDTO dto = new UserDTO();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        dto.setRole(entity.getRole());  // Le rôle est déjà un Enum
        return dto;
    }

    public UserEntity toEntity(UserDTO dto) {
        UserEntity entity = new UserEntity();
        entity.setId(dto.getId());
        entity.setUsername(dto.getUsername());
        entity.setRole(dto.getRole());  // Le rôle est déjà un Enum
        return entity;
    }
}

