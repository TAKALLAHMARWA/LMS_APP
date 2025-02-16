package com.example.userservice.mapper;


import com.example.userservice.dao.Entity.StudentEntity;
import com.example.userservice.dao.Entity.UserEntity;
import com.example.userservice.dto.StudentDTO;
import com.example.userservice.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring")
public interface IStudentMapper {
     StudentDTO toDTO(StudentEntity entity);
     StudentEntity toEntity(StudentDTO dto);
}

