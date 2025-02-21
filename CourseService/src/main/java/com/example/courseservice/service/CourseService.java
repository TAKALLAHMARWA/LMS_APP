package com.example.courseservice.service;

import com.example.courseservice.FeignClient.TeacherClient;
import com.example.courseservice.dao.Entity.CourseEntity;
import com.example.courseservice.dao.Repository.CourseRepository;
import com.example.courseservice.dto.TeacherDTO;
import com.example.courseservice.exception.ResourceNotFoundException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final TeacherClient teacherClient;

    public CourseService(CourseRepository courseRepository, TeacherClient teacherClient) {
        this.courseRepository = courseRepository;
        this.teacherClient = teacherClient;
    }

    private static final String UPLOAD_DIR = "uploads/courses/";
    private static final String COURSE_NOT_FOUND_ERROR = "Course not found with id: ";

    public CourseEntity createCourse(String title, String description, MultipartFile file) throws IOException {
        String pdfUrl = savePdfFile(file);

        CourseEntity course = new CourseEntity();
        course.setTitle(title);
        course.setDescription(description);
        course.setPdfUrl(pdfUrl);

        course = courseRepository.save(course);
        return course;
    }

    public List<CourseEntity> getAllCourses() {
        return courseRepository.findAll();
    }

    private String savePdfFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IOException("Le fichier PDF est vide !");
        }
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(UPLOAD_DIR, fileName);
        Files.createDirectories(filePath.getParent());
        Files.write(filePath, file.getBytes());
        return "/files/" + fileName;
    }

    // Créer un cours
    public CourseEntity createCourse(CourseEntity courseEntity) {
        /**
         * Communication between Course Microservice and User microservice
         * we're using the synchrone communication using a REST API
         */
        TeacherDTO teacher = teacherClient.getTeacherById(String.valueOf(courseEntity.getTeacherId()));
        courseEntity.setTeacherInstanceName(teacher.getInstanceName());
        /**
         * End Communication
         */
        courseEntity.setPublishedAt(LocalDateTime.now()); // Date de publication automatisée
        courseEntity = courseRepository.save(courseEntity);
        generatePdf(courseEntity); // Génération du PDF
        return courseEntity; // Correction du mapper
    }

    // Récupérer un cours par son ID
    public CourseEntity getCourseById(Long courseId) {
        CourseEntity courseEntity = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException(COURSE_NOT_FOUND_ERROR + courseId)); // Constante utilisée
        return courseEntity; // Correction du mapper
    }

    // Générer le PDF pour un cours
    private void generatePdf(CourseEntity courseEntity) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("course_" + courseEntity.getId() + ".pdf"));
            document.open();
            document.add(new Paragraph("Course Title: " + courseEntity.getTitle()));
            document.add(new Paragraph("Description: " + courseEntity.getDescription()));
            document.add(new Paragraph("Published At: " + courseEntity.getPublishedAt()));
            document.close();
        } catch (DocumentException | FileNotFoundException e) {
            // Utilisation de Logger pour une meilleure gestion d'erreurs
            System.err.println("Erreur lors de la génération du PDF : " + e.getMessage());
        }
    }
    // Récupérer le PDF d'un cours par son ID
    public byte[] getCoursePdfById(Long courseId) throws IOException {
        CourseEntity courseEntity = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException(COURSE_NOT_FOUND_ERROR + courseId));

        Path pdfPath = Paths.get(UPLOAD_DIR, courseEntity.getPdfUrl().substring(7));  // Extraire le chemin absolu
        if (Files.exists(pdfPath)) {
            return Files.readAllBytes(pdfPath);  // Retourner le contenu du PDF sous forme de byte[]
        } else {
            throw new FileNotFoundException("Le fichier PDF pour ce cours n'a pas été trouvé.");
        }
    }

    // Supprimer un cours
    public void deleteCourse(Long courseId) {
        CourseEntity courseEntity = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException(COURSE_NOT_FOUND_ERROR + courseId)); // Constante utilisée
        courseRepository.delete(courseEntity);
    }

}