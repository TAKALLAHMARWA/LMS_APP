package com.example.evaluationservice.mapper;

import com.example.evaluationservice.dao.entities.Assignment;
import com.example.evaluationservice.dao.entities.Submission;
import com.example.evaluationservice.dto.SubmissionDTO;
import com.example.evaluationservice.service.AssignmentService;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ISubmissionMapper {
    @Mapping(target = "submittedAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "assignmentId", source = "assignment.id")
    SubmissionDTO toDTO(Submission submission);

    @Mapping(target = "assignment", ignore = true)
    Submission toEntity(SubmissionDTO submissionDTO);
    @AfterMapping
    default void afterMapping(@MappingTarget Submission submission, SubmissionDTO submissionDTO, @Context AssignmentService assignmentService){
        Assignment assignment=assignmentService.getAssignmentById(submissionDTO.getAssignmentId());
        submission.setAssignment(assignment);
    }
}

