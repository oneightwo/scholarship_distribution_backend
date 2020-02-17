package com.oneightwo.scholarship_distribution.controller;

import com.oneightwo.scholarship_distribution.constants.Semester;
import com.oneightwo.scholarship_distribution.distribution.DistributionService;
import com.oneightwo.scholarship_distribution.model.ScienceDirection;
import com.oneightwo.scholarship_distribution.model.Student;
import com.oneightwo.scholarship_distribution.model.University;
import com.oneightwo.scholarship_distribution.security.model.User;
import com.oneightwo.scholarship_distribution.security.repository.UserRepository;
import com.oneightwo.scholarship_distribution.service.FileService;
import com.oneightwo.scholarship_distribution.service.ScienceDirectionService;
import com.oneightwo.scholarship_distribution.service.StudentService;
import com.oneightwo.scholarship_distribution.service.UniversityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/admins/")
public class AdminController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private UniversityService universityService;

    @Autowired
    private ScienceDirectionService scienceDirectionService;

    @Autowired
    private DistributionService distributionService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private FileService fileService;

    private Logger log = LoggerFactory.getLogger(AdminController.class);

    //-----Student-----

    @GetMapping("students")
    private ResponseEntity<?> getStudents() {
        return ResponseEntity.ok().body(studentService.getAll());
    }

    @GetMapping("students/{id}")
    private ResponseEntity<?> getStudent(@PathVariable("id") BigInteger id) {
        return studentService.getById(id).isPresent() ?
                ResponseEntity.ok().body(Optional.of(studentService.getById(id))) :
                ResponseEntity.notFound().build();
    }

    @PutMapping("students/{id}")
    private ResponseEntity<?> updateStudent(
            @PathVariable("id") Student studentFromDB,
            @RequestBody Student student) {
        try {
            BeanUtils.copyProperties(student, studentFromDB, "id", "dataRegistration");
            return ResponseEntity.ok(studentService.update(studentFromDB));
        } catch (Exception e) {
            log.warn("Not found student: ", e);
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("students/{id}")
    private ResponseEntity<?> deleteStudent(@PathVariable("id") BigInteger id) {
        return studentService.deleteById(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    //-----University-----

    @GetMapping("universities")
    private ResponseEntity<?> getUniversities() {
        return ResponseEntity.ok(universityService.getAll());
    }

    @GetMapping("universities/{id}")
    private ResponseEntity<?> getUniversity(@PathVariable("id") BigInteger id) {
        return studentService.getById(id).isPresent() ?
                ResponseEntity.ok().body(Optional.of(universityService.getById(id))) :
                ResponseEntity.notFound().build();
    }

    @PostMapping("universities")
    private ResponseEntity<?> setUniversity(@Valid @RequestBody University university) {
        return ResponseEntity.ok().body(universityService.save(university));
    }

    @PutMapping("universities/{id}")
    private ResponseEntity<?> updateUniversity(
            @PathVariable("id") University universityFromDB,
            @RequestBody University university) {
        try {
            BeanUtils.copyProperties(university, universityFromDB, "id");
            return ResponseEntity.ok(universityService.update(universityFromDB));
        } catch (Exception e) {
            log.warn("Not found university: ", e);
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("universities/{id}")
    private ResponseEntity<?> deleteUniversity(@PathVariable("id") BigInteger id) {
        return universityService.deleteById(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    //-----ScienceDirection-----

    @GetMapping("scienceDirections")
    private ResponseEntity<?> getScienceDirections() {
        return ResponseEntity.ok(scienceDirectionService.getAll());
    }

    @PutMapping("scienceDirections/{id}")
    private ResponseEntity<?> updateScienceDirection(
            @PathVariable("id") ScienceDirection scienceDirectionFromDB,
            @RequestBody ScienceDirection scienceDirection) {
        try {
            BeanUtils.copyProperties(scienceDirection, scienceDirectionFromDB, "id");
            return ResponseEntity.ok(scienceDirectionService.update(scienceDirectionFromDB));
        } catch (Exception e) {
            log.warn("Not found scienceDirections: ", e);
            return ResponseEntity.notFound().build();
        }
    }

    //-----Distribution-----

    @GetMapping("results/winners")
    private ResponseEntity<?> getWinnerStudents(@RequestParam String semester,
                                                @RequestParam int year) {
        return ResponseEntity.ok(distributionService.getWinnerStudents(Semester.valueOf(semester.toUpperCase()), year));
    }

    @GetMapping("results/loser")
    private ResponseEntity<?> getLoserStudents(@RequestParam String semester,
                                                @RequestParam int year) {
        return ResponseEntity.ok(distributionService.getLoserStudents(Semester.valueOf(semester.toUpperCase()), year));
    }

    @GetMapping("reports/directions")
    private ResponseEntity<?> getReportByDirection(@RequestParam String semester,
                                                   @RequestParam int year) {
        return ResponseEntity.ok(distributionService.getReportByDirection(Semester.valueOf(semester.toUpperCase()), year));
    }

    @GetMapping("reports/universities")
    private ResponseEntity<?> getReportByDirectionAndUniversities(@RequestParam String semester,
                                                                  @RequestParam int year) {
        return ResponseEntity.ok(distributionService.getReportByDirectionAndUniversities(Semester.valueOf(semester.toUpperCase()), year));
    }

    //-----Registration-----

    @GetMapping("users")
    private ResponseEntity<?> getUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @PostMapping("registration")
    private ResponseEntity<?> setAdmin(@Valid @RequestBody User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return ResponseEntity.ok().body(userRepository.save(user));
    }

    //-----DownloadFile-----


    @GetMapping("files/download")
    private ResponseEntity<?> getFile(@RequestParam String semester,
                                      @RequestParam int year,
                                      @RequestParam BigInteger id) {
        ByteArrayResource file = fileService.download(year, Semester.valueOf(semester.toUpperCase()), id);
        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=f.jpg");
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");
        return ResponseEntity.ok()
                .headers(header)
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(file);
    }

}
