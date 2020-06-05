package com.oneightwo.scholarship_distribution.controllers;

import com.oneightwo.scholarship_distribution.core.exceptions.CoreException;
import com.oneightwo.scholarship_distribution.model.Student;
import com.oneightwo.scholarship_distribution.security.repository.UserRepository;
import com.oneightwo.scholarship_distribution.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

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

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    private Logger log = LoggerFactory.getLogger(StudentController.class);

    @GetMapping("settings")
    public ResponseEntity<?> getSettings() {
        return ResponseEntity.ok(settingService.getAll());
    }

    @PostMapping("registration")
    public ResponseEntity<?> setStudent(@Valid @RequestBody Student student) {
        log.info("student = {}", student != null ? student : "null");

        if (settingService.isActiveRegistration()) {
            return ResponseEntity.ok(studentService.save(student));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

//    @PostMapping("registr")
//    private ResponseEntity<?> setAdmin(@Valid @RequestBody User user) {
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        return ResponseEntity.ok().body(userRepository.save(user));
//    }

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
        return ResponseEntity.ok().body(courseService.findByNumber());
    }

    @PostMapping("files/upload")
    public ResponseEntity<?> uploadData(@RequestParam("file") MultipartFile file) throws CoreException {
        fileService.upload(file);
        return ResponseEntity.ok().build();
    }

}
