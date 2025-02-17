package com.example.evaluationservice.mapper;

import com.example.evaluationservice.dao.entities.Submission;
import com.example.evaluationservice.dto.SubmissionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ISubmissionMapper {

    @Mapping(target = "submittedAt", expression = "java(java.time.LocalDateTime.now())")
    SubmissionDTO toDTO(Submission submission);

    Submission toEntity(SubmissionDTO submissionDTO);
}

