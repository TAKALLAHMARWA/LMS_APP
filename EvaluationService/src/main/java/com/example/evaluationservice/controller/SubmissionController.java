package com.example.evaluationservice.controller;

import com.example.evaluationservice.dao.entities.Submission;
import com.example.evaluationservice.dto.SubmissionDTO;
import com.example.evaluationservice.mapper.ISubmissionMapper;
import com.example.evaluationservice.service.SubmissionService;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/submissions")
public class SubmissionController {
    private final SubmissionService submissionService;
    private final ISubmissionMapper submissionMapper;
    public SubmissionController(SubmissionService submissionService, ISubmissionMapper submissionMapper) {
        this.submissionService = submissionService;
        this.submissionMapper = submissionMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubmissionDTO> get(@PathVariable Long id) {
        return ResponseEntity.of(Optional.of(submissionMapper.toDTO(submissionService.getSubmissionById(id))));
    }
    @GetMapping
    public List<SubmissionDTO> all() {
        return submissionService.getAllSubmissions().stream().map(submissionMapper::toDTO).collect(Collectors.toList());
    }
    @PostMapping("/{studentId}/{assignmentId}")
    public ResponseEntity<Submission> submitAssignment(
            @PathVariable String studentId,
            @PathVariable Long assignmentId,
            @RequestParam("file") MultipartFile file) {
        try {
            Submission submission = submissionService.submitAssignment(studentId, assignmentId, file);
            return ResponseEntity.ok(submission);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        submissionService.deleteSubmission(id);
    }
}
