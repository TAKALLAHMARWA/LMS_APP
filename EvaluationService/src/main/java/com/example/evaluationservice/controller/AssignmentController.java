package com.example.evaluationservice.controller;

import com.example.evaluationservice.dao.entities.Assignment;
import com.example.evaluationservice.dto.AssignmentDTO;
import com.example.evaluationservice.mapper.IAssignmentMapper;
import com.example.evaluationservice.service.AssignmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/assignments")
public class  AssignmentController {
    private final AssignmentService assignmentservice;
    private final IAssignmentMapper assignmentMapper;
    public AssignmentController(AssignmentService assignmentservice, IAssignmentMapper assignmentMapper) {
        this.assignmentservice = assignmentservice;
        this.assignmentMapper = assignmentMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssignmentDTO> get(@PathVariable Long id) {
        return ResponseEntity.of(Optional.of(assignmentMapper.toDto(assignmentservice.getAssignmentById(id))));
    }
    @GetMapping
    public List<AssignmentDTO> all() {
        return assignmentservice.getAllAssignments().stream().map(assignmentMapper::toDto).collect(Collectors.toList());
    }
    @PostMapping
    public AssignmentDTO create(@RequestBody AssignmentDTO assignmentDTO) throws IOException {
        Assignment assignmentEntity= assignmentMapper.toEntity(assignmentDTO);
        return assignmentMapper.toDto(assignmentservice.createAssignment(assignmentEntity));
    }
    @PostMapping("/batch")
    public List<AssignmentDTO> createAll(@RequestBody List<AssignmentDTO> assignmentDTOS) {
        return assignmentDTOS.stream().map(assignmentDTO -> {
                    try {
                        Assignment assignmentEntity= assignmentMapper.toEntity(assignmentDTO);
                        return assignmentMapper.toDto(assignmentservice.createAssignment(assignmentEntity));
                    } catch (Exception e) {
                        // Log the exception (optional)
                        System.err.println("Error processing assignment: " + assignmentDTO.getTitle() + " - " + e.getMessage());
                        return null; // or handle differently, like returning a specific error DTO
                    }
                })
                .filter(Objects::nonNull) // Filter out any nulls (in case of exceptions)
                .collect(Collectors.toList());
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        assignmentservice.deleteAssignment(id);
    }
}
