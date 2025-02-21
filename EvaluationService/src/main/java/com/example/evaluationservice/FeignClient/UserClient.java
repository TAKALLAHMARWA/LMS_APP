package com.example.evaluationservice.FeignClient;

import com.example.evaluationservice.dto.StudentDTO;
import com.example.evaluationservice.dto.TeacherDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
    @FeignClient(name = "user-service")
    public interface UserClient {
        @GetMapping("/api/students/{username}")
        StudentDTO getStudentById(@PathVariable String username);
        @GetMapping("/api/teachers/{username}")
        TeacherDTO getTeacherById(@PathVariable String username);
    }

