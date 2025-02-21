package com.example.courseservice.dto;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class CourseDTO {
    private Long id;
    private String title;
    private String description;
    private String pdfUrl;
    private LocalDateTime publishedAt;
    private String teacherId;
    private String teacherInstanceName;
}


