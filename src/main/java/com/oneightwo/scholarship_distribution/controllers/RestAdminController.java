package com.oneightwo.scholarship_distribution.controllers;

import com.oneightwo.scholarship_distribution.core.exceptions.CoreException;
import com.oneightwo.scholarship_distribution.core.helpers.TransformationHelper;
import com.oneightwo.scholarship_distribution.data_view.services.DataService;
import com.oneightwo.scholarship_distribution.students.models.Student;
import com.oneightwo.scholarship_distribution.students.models.StudentDTO;
import com.oneightwo.scholarship_distribution.students.services.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequestMapping("/admins")
@RestController
public class RestAdminController {

    private final StudentService studentService;
    private final DataService dataService;

    public RestAdminController(StudentService studentService, DataService dataService) {
        this.studentService = studentService;
        this.dataService = dataService;
    }

    @GetMapping("/test")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok(dataService.execute());
    }

//    @GetMapping("/students/{id}")
//    public ResponseEntity<?> getStudentById(@PathVariable Long id) {
//        return ResponseEntity.ok(dataService.execute(id));
//    }

//    @GetMapping("/students")
//    public ResponseEntity<?> getParticipants(@PageableDefault(size = Integer.MAX_VALUE)
//                                             @SortDefault(sort = "surname", direction = Sort.Direction.ASC) Pageable pageable) {
//        return ResponseEntity.ok(dataService.execute(id));
//    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) throws CoreException {
        studentService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable Long id, @Valid @RequestBody StudentDTO student) throws CoreException {
        student.setId(id);
        Student update = studentService.update(TransformationHelper.dtoToObject(student));
        log.info("updateStudent = {}", update);
        return ResponseEntity.ok(TransformationHelper.objectToDto(update));
    }

}
