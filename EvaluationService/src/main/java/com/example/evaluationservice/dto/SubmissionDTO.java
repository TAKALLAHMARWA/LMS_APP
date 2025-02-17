package com.example.evaluationservice.dto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class SubmissionDTO {
    private Long id;
    private String fileUrl; // Stockage du fichier
    private LocalDateTime submittedAt;
    private String studentId;
    private String assignment;
}
