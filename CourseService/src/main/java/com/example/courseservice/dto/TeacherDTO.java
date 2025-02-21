package com.example.courseservice.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TeacherDTO{
    private Long id;
    private String username;
    private Role role;
    private String instanceName;
}

