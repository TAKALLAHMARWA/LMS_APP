package com.example.userservice.service;


import com.example.userservice.dao.Entity.StudentEntity;
import com.example.userservice.dao.Repository.StudentRepository;
import com.example.userservice.exeption.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public StudentEntity create(StudentEntity studentEntity) {
        studentEntity = studentRepository.save(studentEntity);
        return studentEntity;
    }
    public StudentEntity getByUsername(String username) {
        StudentEntity userEntity = studentRepository.findStudentEntityByUsername(username);
        if (userEntity == null) {
            throw new ResourceNotFoundException("Student not found with username: " + username);
        }
        return userEntity;
    }
    public List<StudentEntity> all() {
        return studentRepository.findAll();
    }
    public void delete(Long studentID) {
        StudentEntity userEntity = studentRepository.findById(studentID)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + studentID));
        studentRepository.delete(userEntity);
    }
}
