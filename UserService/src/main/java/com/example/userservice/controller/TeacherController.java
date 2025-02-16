package com.example.userservice.controller;

import com.example.userservice.dao.Entity.TeacherEntity;
import com.example.userservice.dto.TeacherDTO;
import com.example.userservice.mapper.ITeacherMapper;
import com.example.userservice.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {
    private final TeacherService teacherService;
    private final ITeacherMapper teacherMapper;
    public TeacherController(TeacherService teacherService, ITeacherMapper teacherMapper) {
        this.teacherService = teacherService;
        this.teacherMapper = teacherMapper;
    }

    @GetMapping("/{username}")
    public ResponseEntity<TeacherDTO> get(@PathVariable String username) {
        return ResponseEntity.of(Optional.of(teacherMapper.toDTO(teacherService.getByUsername(username))));
    }
    @GetMapping
    public List<TeacherDTO> all() {
        return teacherService.all().stream().map(teacherMapper::toDTO).collect(Collectors.toList());
    }
    @PostMapping
    public TeacherDTO create(@RequestBody TeacherDTO teacherDTO) {
        TeacherEntity teacherEntity= teacherMapper.toEntity(teacherDTO);
        return teacherMapper.toDTO(teacherService.create(teacherEntity));
    }
    @DeleteMapping("/{teacherId}")
    public void delete(@PathVariable Long teacherId) {
        teacherService.delete(teacherId);
    }
}
