package com.example.userservice.controller;

import com.example.userservice.dao.Entity.StudentEntity;
import com.example.userservice.dto.StudentDTO;
import com.example.userservice.mapper.IStudentMapper;
import com.example.userservice.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping("/api/students")
public class StudentController {
    private StudentService studentService;
    private IStudentMapper studentMapper;
    @GetMapping("/{username}")
    public StudentDTO getUser(@PathVariable String username) {
        return studentMapper.toDTO(studentService.getByUsername(username));
    }
    @GetMapping
    public List<StudentDTO> all(){
        List<StudentEntity> allStudents=studentService.all();
        return allStudents.stream().map(studentMapper::toDTO).toList();
    }
    @PostMapping
    public StudentDTO createUser(@RequestBody StudentDTO studentDTO) {
        StudentEntity studentEntity=studentMapper.toEntity(studentDTO);
        studentEntity=studentService.create(studentEntity);
        return studentMapper.toDTO(studentEntity);
    }
    @DeleteMapping("/{studentId}")
    public void deleteUser(@PathVariable Long studentId) {
        studentService.delete(studentId);
    }
}
