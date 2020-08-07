package com.oneightwo.scholarship_distribution.controllers;

import com.oneightwo.scholarship_distribution.core.exceptions.CoreException;
import com.oneightwo.scholarship_distribution.distribution.constants.Semester;
import com.oneightwo.scholarship_distribution.distribution.reports.services.ReportByScienceDirectionsService;
import com.oneightwo.scholarship_distribution.distribution.reports.services.ReportByUniversitiesService;
import com.oneightwo.scholarship_distribution.distribution.reports.services.ReportByWinningStudentsService;
import com.oneightwo.scholarship_distribution.files_storage.services.FileService;
import com.oneightwo.scholarship_distribution.settings.services.SettingService;
import com.oneightwo.scholarship_distribution.science_directions.services.ScienceDirectionService;
import com.oneightwo.scholarship_distribution.students.services.StudentService;
import com.oneightwo.scholarship_distribution.universities.services.UniversityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/students/")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ScienceDirectionService scienceDirectionService;

    @Autowired
    private UniversityService universityService;

//    @Autowired
//    private CourseService courseService;

    @Autowired
    private FileService fileService;

    @Autowired
    private SettingService settingService;

    @Autowired
    private ReportByScienceDirectionsService reportByScienceDirectionsService;

    @GetMapping("test1")
    public ResponseEntity<?> setStudent1() {
        return ResponseEntity.ok(reportByScienceDirectionsService.execute(Semester.SPRING, 2020));
    }
    @Autowired
    private ReportByUniversitiesService reportByUniversitiesService;

    @GetMapping("test2")
    public ResponseEntity<?> setStudent2() {
        return ResponseEntity.ok(reportByUniversitiesService.execute(Semester.SPRING, 2020));
    }

    @Autowired
    private ReportByWinningStudentsService reportByWinningStudentsService;

    @GetMapping("test3")
    public ResponseEntity<?> setStudent3() {
        return ResponseEntity.ok(reportByWinningStudentsService.execute(Semester.SPRING, 2020));
    }

//    @Autowired4

//    @Autowired
//    private UserRepository userRepository;

    private Logger log = LoggerFactory.getLogger(StudentController.class);

    @GetMapping("settings")
    public ResponseEntity<?> getSettings() {
        return ResponseEntity.ok(settingService.getAll());
    }

//    @PostMapping("registration")
//    public ResponseEntity<?> setStudent(@Valid @RequestBody StudentDTO student) {
//        log.info("student = {}", student != null ? student : "null");
//
////        if (settingService.isActiveRegistration()) {
////            return ResponseEntity.ok(studentService.save(student));
////        } else {
//            return ResponseEntity.badRequest().build();
////        }
//    }

//    @PostMapping("registr")
//    private ResponseEntity<?> setAdmin(@Valid @RequestBody User user) {
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        return ResponseEntity.ok().body(userRepository.save(user));
//    }

    @GetMapping("fields/universities")
    private ResponseEntity<?> getScienceDirection() {
        return ResponseEntity.ok().body(universityService.getExisting());
    }

    @GetMapping("fields/science_directions")
    private ResponseEntity<?> getUniversities() {
        return ResponseEntity.ok().body(scienceDirectionService.getExisting());
    }

//    @GetMapping("fields/courses")
//    private ResponseEntity<?> getCourses() {
//        return ResponseEntity.ok().body(courseService.findByNumber());
//    }

    @PostMapping("files/upload")
    public ResponseEntity<?> uploadData(@RequestParam("file") MultipartFile file) throws CoreException {
        fileService.upload(file);
        return ResponseEntity.ok().build();
    }

}
