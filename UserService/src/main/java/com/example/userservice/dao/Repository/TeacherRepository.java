package com.example.userservice.dao.Repository;
import com.example.userservice.dao.Entity.TeacherEntity;
import com.example.userservice.dao.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<TeacherEntity, Long> {
    TeacherEntity findTeacherEntitiesByUsername(String username);
}

