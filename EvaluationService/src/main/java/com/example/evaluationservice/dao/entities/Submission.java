package com.example.evaluationservice.dao.entities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name= "submission")
@Getter
@Setter
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileUrl; // Stockage du fichier
    private LocalDateTime submittedAt;

    @Column(name = "teacher_id", nullable = false)
    private String student;

    @ManyToOne
    private Assignment assignment;

}
