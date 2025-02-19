package com.example.evaluationservice.service;
import com.example.evaluationservice.FeignClient.TeacherClient;
import com.example.evaluationservice.dao.entities.Assignment;
import com.example.evaluationservice.dao.repositories.AssignmentRepository;
import com.example.evaluationservice.dto.TeacherDTO;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final TeacherClient teacherClient;
    private static final String ASSIGNMENT_NOT_FOUND = "Assignment not found with id: ";
    public AssignmentService(AssignmentRepository assignmentRepository, TeacherClient teacherClient) {
        this.assignmentRepository = assignmentRepository;
        this.teacherClient = teacherClient;
    }


    public Assignment createAssignment(Assignment assignmentEntity)throws IOException {
        TeacherDTO teacher = teacherClient.getTeacherById(assignmentEntity.getTeacherId());
        if (teacher == null) {
            throw new ResourceNotFoundException("Teacher not found with id: " + assignmentEntity.getTeacherId());
        }

        assignmentEntity.setTeacherId(assignmentEntity.getTeacherId());
        assignmentEntity.setCreatedAt(LocalDateTime.now());

        return assignmentRepository.save(assignmentEntity);
    }

    // Récupérer tous les devoirs
    public List<Assignment> getAllAssignments() {
        return assignmentRepository.findAll();
    }

    // Récupérer un devoir par ID
    public Assignment getAssignmentById(Long assignmentId) {
        return assignmentRdeepository.findById(assignmentId)
                .orElseThrow(() -> new ResourceNotFoundException(ASSIGNMENT_NOT_FOUND + assignmentId));
    }

    // Supprimer un devoir
    public void deleteAssignment(Long assignmentId) {
        Assignment assignmentEntity = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new ResourceNotFoundException(ASSIGNMENT_NOT_FOUND + assignmentId));
        assignmentRepository.delete(assignmentEntity);
    }
}

