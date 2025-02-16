package com.example.userservice.dto;

import com.example.userservice.dao.Entity.Role;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TeacherDTO extends UserDTO{
    private Long id;
}

