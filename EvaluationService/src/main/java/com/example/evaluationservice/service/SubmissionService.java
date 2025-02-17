package com.example.evaluationservice.service;

import com.example.evaluationservice.FeignClient.StudentClient;
import com.example.evaluationservice.dao.entities.Submission;
import com.example.evaluationservice.dao.repositories.SubmissionRepository;
import com.example.evaluationservice.dto.StudentDTO;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SubmissionService {

    private final SubmissionRepository submissionRepository;
    private final StudentClient studentClient;

    private static final String UPLOAD_DIR = "uploads/submissions/";
    private static final String SUBMISSION_NOT_FOUND = "Submission not found with id: ";

    public SubmissionService(SubmissionRepository submissionRepository, StudentClient studentClient) {
        this.submissionRepository = submissionRepository;
        this.studentClient = studentClient;
    }

    // Soumission d'un devoir par un étudiant
    public Submission submitAssignment(Long studentId, Long assignmentId, MultipartFile file) throws IOException {
        StudentDTO student = studentClient.getStudentById(studentId);
        if (student == null) {
            throw new ResourceNotFoundException("Student not found with id: " + studentId);
        }

        String fileUrl = saveFile(file);

        Submission submissionEntity = new Submission();
        submissionEntity.setAssignment(assignmentId);
        submissionEntity.setStudentId(studentId);
        submissionEntity.setFileUrl(fileUrl);
        submissionEntity.setSubmittedAt(LocalDateTime.now());

        return submissionRepository.save(submissionEntity);
    }

    // Récupérer toutes les soumissions
    public List<Submission> getAllSubmissions() {
        return submissionRepository.findAll();
    }

    // Récupérer une soumission par ID
    public Submission getSubmissionById(Long submissionId) {
        return submissionRepository.findById(submissionId)
                .orElseThrow(() -> new ResourceNotFoundException(SUBMISSION_NOT_FOUND + submissionId));
    }

    // Supprimer une soumission
    public void deleteSubmission(Long submissionId) {
        Submission submissionEntity = submissionRepository.findById(submissionId)
                .orElseThrow(() -> new ResourceNotFoundException(SUBMISSION_NOT_FOUND + submissionId));
        submissionRepository.delete(submissionEntity);
    }

    // Sauvegarder un fichier (ZIP ou PDF)
    private String saveFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IOException("Le fichier est vide !");
        }
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(UPLOAD_DIR, fileName);
        Files.createDirectories(filePath.getParent());
        Files.write(filePath, file.getBytes());
        return "/files/" + fileName;
    }
}
