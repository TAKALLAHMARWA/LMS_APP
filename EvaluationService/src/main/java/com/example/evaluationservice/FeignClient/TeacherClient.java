package com.example.evaluationservice.FeignClient;

import com.example.evaluationservice.dto.TeacherDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "TeacherService", url = "http://localhost:8085/api/teachers")
public interface TeacherClient {
    @GetMapping("/{username}")
    TeacherDTO getTeacherById(@PathVariable String username);
}