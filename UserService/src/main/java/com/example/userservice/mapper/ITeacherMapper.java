package com.example.userservice.mapper;


import com.example.userservice.dao.Entity.TeacherEntity;
import com.example.userservice.dao.Entity.UserEntity;
import com.example.userservice.dto.TeacherDTO;
import com.example.userservice.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring")
public interface ITeacherMapper {
     TeacherDTO toDTO(TeacherEntity entity);
     TeacherEntity toEntity(TeacherDTO dto);
}

