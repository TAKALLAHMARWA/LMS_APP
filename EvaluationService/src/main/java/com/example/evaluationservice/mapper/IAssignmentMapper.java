package com.example.evaluationservice.mapper;

import com.example.evaluationservice.dao.entities.Assignment;
import com.example.evaluationservice.dto.AssignmentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IAssignmentMapper {
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    AssignmentDTO toDto(Assignment assignment);
    Assignment toEntity(AssignmentDTO assignmentDTO);
}
