package com.example.userservice.dao.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "teachers") // Table spécifique pour les enseignants
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeacherEntity extends UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
}

