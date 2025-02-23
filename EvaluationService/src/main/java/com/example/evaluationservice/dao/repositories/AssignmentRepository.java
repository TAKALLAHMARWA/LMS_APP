package com.example.evaluationservice.dao.repositories;

import com.example.evaluationservice.dao.entities.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
}

