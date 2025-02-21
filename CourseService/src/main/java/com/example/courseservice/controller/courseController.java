package com.example.courseservice.controller;
import com.example.courseservice.dto.CourseDTO;
import com.example.courseservice.mapper.CourseMapper;
import com.example.courseservice.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/courses")
public class courseController {

    private final CourseService courseService;
    private final CourseMapper courseMapper;

    @Autowired
    public courseController(CourseService courseService, CourseMapper courseMapper) {
        this.courseService = courseService;
        this.courseMapper = courseMapper;
    }

    // Endpoint pour créer un cours
    @PostMapping
    public CourseDTO createCourse(@RequestBody CourseDTO courseDTO)
    {
        return courseMapper.toDTO(courseService.createCourse(courseMapper.toEntity(courseDTO)));
    }
    @PostMapping("/batch")
    public List<CourseDTO> createAll(@RequestBody List<CourseDTO> courseDTOS) {
        return courseDTOS.stream().map(courseDTO -> {
                    try {
                        return courseMapper.toDTO(courseService.createCourse(courseMapper.toEntity(courseDTO)));
                    } catch (Exception e) {
                        // Log the exception (optional)
                        System.err.println("Error processing course: " + courseDTO.getTitle() + " - " + e.getMessage());
                        return null; // or handle differently, like returning a specific error DTO
                    }
                })
                .filter(Objects::nonNull) // Filter out any nulls (in case of exceptions)
                .collect(Collectors.toList());
    }

    // Endpoint pour récupérer un cours par son ID
    @GetMapping("/{courseId}")
    public CourseDTO getCourseById(@PathVariable Long courseId)
    {
        return courseMapper.toDTO(courseService.getCourseById(courseId));
    }
    @GetMapping
    public List<CourseDTO> all()
    {
        return courseService.getAllCourses().stream().map(courseMapper::toDTO).toList();
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

