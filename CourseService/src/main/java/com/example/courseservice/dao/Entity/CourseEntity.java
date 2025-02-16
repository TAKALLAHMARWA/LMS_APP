package com.example.courseservice.dao.Entity;
import com.example.userservice.dao.Entity.UserEntity;
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

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private UserEntity teacher;
}
