package com.oneightwo.scholarship_distribution.controller;

import com.oneightwo.scholarship_distribution.constants.Semester;
import com.oneightwo.scholarship_distribution.model.Student;
import com.oneightwo.scholarship_distribution.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigInteger;

@RestController
@RequestMapping("api/v1/students/")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ScienceDirectionService scienceDirectionService;

    @Autowired
    private UniversityService universityService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private FileService fileService;

    @Autowired
    private SettingService settingService;

    private Logger log = LoggerFactory.getLogger(StudentController.class);

    @PostMapping("registration")
    public ResponseEntity<?> setStudent( @RequestPart("student") Student student, @RequestPart("file") MultipartFile file) {
        log.info("student = {}", student != null ? student : "null");
        log.info("file = {}", file != null ? file.getOriginalFilename() : "null");

        if (settingService.isActiveRegistration() && file != null && !file.isEmpty()) {
            Student saveStudent = studentService.save(student);
            BigInteger id = saveStudent.getId();
            boolean isSaveFile = fileService.upload(file, String.valueOf(id));
            return isSaveFile ? ResponseEntity.ok().body(studentService.save(saveStudent)) : ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("fields/universities")
    private ResponseEntity<?> getScienceDirection() {
        return ResponseEntity.ok().body(universityService.getAll());
    }

    @GetMapping("fields/science_directions")
    private ResponseEntity<?> getUniversities() {
        return ResponseEntity.ok().body(scienceDirectionService.getAll());
    }

    @GetMapping("fields/courses")
    private ResponseEntity<?> getCourses() {
        return ResponseEntity.ok().body(courseService.getAll());
    }

//    @PostMapping("/files/upload")
//    public ResponseEntity<?> uploadData(@RequestParam("file") MultipartFile file) {
//        if (fileService.upload(file, "EMPTY_NAME")) {
//            return ResponseEntity.ok().build();
//        } else {
//            return ResponseEntity.notFound().build(); //CHANGE HANDLER ERRORS
//        }
//    }

}
