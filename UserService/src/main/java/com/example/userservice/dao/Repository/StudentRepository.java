package com.example.userservice.dao.Repository;
import com.example.userservice.dao.Entity.StudentEntity;
import com.example.userservice.dao.Entity.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
    StudentEntity findStudentEntityByUsername(String username);
}

