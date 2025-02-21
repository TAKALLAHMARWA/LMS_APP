package com.example.courseservice.FeignClient;

import com.example.courseservice.dto.TeacherDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")
public interface TeacherClient {
    @GetMapping("/api/teachers/{username}")
    TeacherDTO getTeacherById(@PathVariable String username);
}

