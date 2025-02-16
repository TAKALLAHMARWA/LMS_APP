package com.example.userservice.service;


import com.example.userservice.dao.Entity.TeacherEntity;
import com.example.userservice.dao.Repository.TeacherRepository;
import com.example.userservice.exeption.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public TeacherEntity create(TeacherEntity teacherEntity) {
        teacherEntity = teacherRepository.save(teacherEntity);
        return teacherEntity;
    }
    public TeacherEntity getByUsername(String username) {
        TeacherEntity userEntity = teacherRepository.findTeacherEntitiesByUsername(username);
        if (userEntity == null) {
            throw new EntityNotFoundException("User not found with username: " + username);
        }
        return userEntity;
    }
    public List<TeacherEntity> all() {
        return teacherRepository.findAll();
    }
    public void delete(Long teacherId) {
        TeacherEntity userEntity = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found with id: " + teacherId));
        teacherRepository.delete(userEntity);
    }
}
