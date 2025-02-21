package com.example.courseservice.dao.Repository;
import com.example.courseservice.dao.Entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CourseRepository extends JpaRepository<CourseEntity, Long> {
    List<CourseEntity> findByTeacherId(String teacherId);  // Récupérer les cours d’un professeur
}

