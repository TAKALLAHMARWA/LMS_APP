package com.example.evaluationservice.FeignClient;

import com.example.evaluationservice.dto.TeacherDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


    @FeignClient(name = "UserService", url = "http://localhost:8085/api/students")
    public interface StudentClient {
        @GetMapping("/{username}")
        TeacherDTO getStudentById(@PathVariable String username);
    }

