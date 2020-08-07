package com.oneightwo.scholarship_distribution.controllers;

import com.oneightwo.scholarship_distribution.core.exceptions.CoreException;
import com.oneightwo.scholarship_distribution.distribution.constants.Semester;
import com.oneightwo.scholarship_distribution.files_storage.constants.Constants;
import com.oneightwo.scholarship_distribution.files_storage.services.FileService;
import com.oneightwo.scholarship_distribution.science_directions.models.ScienceDirection;
import com.oneightwo.scholarship_distribution.science_directions.services.ScienceDirectionService;
import com.oneightwo.scholarship_distribution.settings.models.Settings;
import com.oneightwo.scholarship_distribution.settings.services.SettingService;
import com.oneightwo.scholarship_distribution.students.services.StudentService;
import com.oneightwo.scholarship_distribution.universities.models.University;
import com.oneightwo.scholarship_distribution.universities.services.UniversityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("api/v2/admins/")
@Slf4j
public class AdminController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private UniversityService universityService;
    @Autowired
    private ScienceDirectionService scienceDirectionService;
//    @Autowired
//    private DistributionService distributionService;
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private FileService fileService;
    @Autowired
    private SettingService settingService;

    //-----Settings-----

    @PostMapping("settings")
    private ResponseEntity<?> changeSetting(@RequestBody Settings settings) {
        return ResponseEntity.ok(settingService.changeSettings(settings));
    }

    //-----Student-----

//    @GetMapping("students/current")
//    private ResponseEntity<?> getCurrentStudents(@PageableDefault(page = 0, size = 100)//ОШИБКА: столбец "ddf" не существует
//                                                 @SortDefault(sort = "surname", direction = Sort.Direction.ASC) Pageable pageable) {
//        List<Student> studentByMonthsAndYear = studentService.getStudentsBySemesterAndYear(Semester.getSemesterByMonth(LocalDate.now().getMonthValue()), LocalDate.now().getYear(), pageable);
//        return ResponseEntity.ok().body(studentByMonthsAndYear);
//    }

//    @GetMapping("students")
//    private ResponseEntity<?> getStudents(@RequestParam int year,
//                                          @RequestParam String semester,
//                                          @PageableDefault(page = 0, size = 100)//ОШИБКА: столбец "ddf" не существует
//                                          @SortDefault(sort = "surname", direction = Sort.Direction.ASC) Pageable pageable) throws SemesterNotFoundException {
//        return ResponseEntity.ok().body(studentService.getStudentsBySemesterAndYear(Semester.getSemesterByName(semester), year, pageable));
//    }

//    @GetMapping("students")
//    private ResponseEntity<?> getStudents(@RequestParam int year,
//                                          @RequestParam String semester,
//                                          @PageableDefault(page = 0, size = 100)//ОШИБКА: столбец "ddf" не существует
//                                          @SortDefault(sort = "surname", direction = Sort.Direction.ASC) Pageable pageable) {
//        return ResponseEntity.ok().body(studentService.getStudentsBySemesterAndYear(Semester.getSemesterByName(semester), year, pageable));
//    }

    @GetMapping("students/{id}")
    private ResponseEntity<?> getStudent(@PathVariable Long id) throws CoreException {
        return ResponseEntity.ok().body(Optional.of(studentService.getById(id)));
    }

//    @PutMapping("students/{id}")
//    private ResponseEntity<?> updateStudent(@PathVariable("id") Student studentFromDB,
//                                            @RequestBody Student student) throws StudentNotFoundException {
//        try {
//            BeanUtils.copyProperties(student, studentFromDB, "id", "dataRegistration");
//            return ResponseEntity.ok(studentService.update(studentFromDB));
//        } catch (Exception e) {
//            throw new StudentNotFoundException();
//        }
//    }

    @DeleteMapping("students/{id}")
    private ResponseEntity<?> deleteStudent(@PathVariable Long id) throws CoreException {
        studentService.delete(id);
        return ResponseEntity.ok().build();
    }

    //-----University-----

    @GetMapping("universities")
    private ResponseEntity<?> getUniversities() {
        return ResponseEntity.ok(universityService.getExisting());
    }

    @GetMapping("universities/{id}")
    private ResponseEntity<?> getUniversity(@PathVariable Long id) throws CoreException {
        return ResponseEntity.ok().body(Optional.of(universityService.getById(id)));
    }

    @PostMapping("universities")
    private ResponseEntity<?> setUniversity(@Valid @RequestBody University university) {
        return ResponseEntity.ok().body(universityService.save(university));
    }

    @PutMapping("universities/{id}")
    private ResponseEntity<?> updateUniversity(@PathVariable("id") University universityFromDB,
                                               @RequestBody University university) {
        try {
            BeanUtils.copyProperties(university, universityFromDB, "id");
            return ResponseEntity.ok(universityService.update(universityFromDB));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

//    @DeleteMapping("universities/{id}")
//    private ResponseEntity<?> deleteUniversity(@PathVariable Long id) {
//        return universityService.deleteById(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
//    }

    //-----ScienceDirection-----

//    @GetMapping("scienceDirections")
//    private ResponseEntity<?> getScienceDirections() {
//        return ResponseEntity.ok(scienceDirectionService.getAll());
//    }

    @PutMapping("scienceDirections/{id}")
    private ResponseEntity<?> updateScienceDirection(@PathVariable("id") ScienceDirection scienceDirectionFromDB,
                                                     @RequestBody ScienceDirection scienceDirection) {
        try {
            BeanUtils.copyProperties(scienceDirection, scienceDirectionFromDB, "id");
            return ResponseEntity.ok(scienceDirectionService.update(scienceDirectionFromDB));
        } catch (Exception e) {
//            log.warn("Not found scienceDirections: ", e);
            return ResponseEntity.notFound().build();
        }
    }

    //-----Distribution-----

//    @GetMapping("results/winners")
//    private ResponseEntity<?> getWinnerStudents(@RequestParam String semester,
//                                                @RequestParam int year) throws SemesterNotFoundException {
//        return ResponseEntity.ok(distributionService.getWinnerStudents(Semester.getSemesterByName(semester), year));
//    }
//
//    @GetMapping("results/winners/current")
//    private ResponseEntity<?> getCurrentWinnerStudents() {
//        return ResponseEntity.ok(distributionService.getWinnerStudents(Semester.getSemesterByMonth(LocalDate.now().getMonthValue()), LocalDate.now().getYear()));
//    }
//
//    @GetMapping("results/loser")
//    private ResponseEntity<?> getLoserStudents(@RequestParam String semester,
//                                               @RequestParam int year) throws SemesterNotFoundException {
//        return ResponseEntity.ok(distributionService.getLoserStudents(Semester.getSemesterByName(semester), year));
//    }
//
//    @GetMapping("results/loser/current")
//    private ResponseEntity<?> getCurrentLoserStudents() {
//        return ResponseEntity.ok(distributionService.getLoserStudents(Semester.getSemesterByMonth(LocalDate.now().getMonthValue()), LocalDate.now().getYear()));
//    }
//
//    @GetMapping("reports/directions")
//    private ResponseEntity<?> getReportByDirection(@RequestParam String semester,
//                                                   @RequestParam int year) throws SemesterNotFoundException {
//        return ResponseEntity.ok(distributionService.getReportByDirection(Semester.getSemesterByName(semester), year));
//    }
//
//    @GetMapping("reports/directions/current")
//    private ResponseEntity<?> getCurrentReportByDirection() {
//        return ResponseEntity.ok(distributionService.getReportByDirection(Semester.getSemesterByMonth(LocalDate.now().getMonthValue()), LocalDate.now().getYear()));
//    }
//
//    @GetMapping("reports/universities")
//    private ResponseEntity<?> getReportByDirectionAndUniversities(@RequestParam String semester,
//                                                                  @RequestParam int year) throws SemesterNotFoundException {
//        return ResponseEntity.ok(distributionService.getReportByDirectionAndUniversities(Semester.getSemesterByName(semester), year));
//    }
//
//    @GetMapping("reports/universities/current")
//    private ResponseEntity<?> getCurrentReportByDirectionAndUniversities() {
//        return ResponseEntity.ok(distributionService.getReportByDirectionAndUniversities(Semester.getSemesterByMonth(LocalDate.now().getMonthValue()), LocalDate.now().getYear()));
//    }

    //-----Registration-----

//    @GetMapping("users")
//    private ResponseEntity<?> getUsers() {
//        return ResponseEntity.ok(userRepository.findAll());
//    }
//
//    @PostMapping("registration")
//    private ResponseEntity<?> setAdmin(@Valid @RequestBody User user) {
////        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        return ResponseEntity.ok().body(userRepository.save(user));
//    }

    //-----DownloadFile-----

    @GetMapping("files/download")
    private ResponseEntity<?> getFile(@RequestParam String semester,
                                      @RequestParam String year,
                                      @RequestParam String name) throws CoreException, IOException {
        ByteArrayOutputStream file = fileService.download(year, Semester.getSemesterByName(semester), name);
        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + name + Constants.PDF_EXTENSION);
        return ResponseEntity.ok()
                .headers(header)
                .body(file.toByteArray());
    }

}
