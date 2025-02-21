package com.example.courseservice.dao.Entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "courses")
@Getter
@Setter
public class CourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;

    @Column(name = "pdf_url")
    private String pdfUrl;  // Lien vers le fichier PDF (Google Drive ou stockage local)

    @Column(name = "published_at")
    private LocalDateTime publishedAt = LocalDateTime.now(); //

    @Column(name = "teacher_id", nullable = false)
    private String teacherId;  // Instead of @ManyToOne
    private String teacherInstanceName;
}
