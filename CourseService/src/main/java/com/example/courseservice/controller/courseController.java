package com.example.courseservice.controller;
import com.example.courseservice.dto.CourseDTO;
import com.example.courseservice.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/courses")
public class courseController {

    private final CourseService courseService;

    @Autowired
    public courseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // Endpoint pour créer un cours
    @PostMapping
    public CourseDTO createCourse(@RequestBody CourseDTO courseDTO)
    {
        return courseService.createCourse(courseDTO);
    }

    // Endpoint pour récupérer un cours par son ID
    @GetMapping("/{courseId}")
    public CourseDTO getCourseById(@PathVariable Long courseId)
    {
        return courseService.getCourseById(courseId);
    }

    @GetMapping("/{courseId}/download")
    public byte[] downloadCoursePdf(@PathVariable Long courseId) throws IOException {
        return courseService.getCoursePdfById(courseId);  // Méthode pour récupérer le PDF
    }
    // Endpoint pour supprimer un cours
    @DeleteMapping("/{courseId}")
    public void deleteCourse(@PathVariable Long courseId) {
        courseService.deleteCourse(courseId);
    }
}

