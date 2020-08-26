package com.oneightwo.scholarship_distribution.controllers;

import com.oneightwo.scholarship_distribution.core.helpers.TransformationHelper;
import com.oneightwo.scholarship_distribution.data_view.services.DataService;
import com.oneightwo.scholarship_distribution.data_view.services.StudentRegistrationService;
import com.oneightwo.scholarship_distribution.students.models.StudentDTO;
import com.oneightwo.scholarship_distribution.students.services.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Controller
public class StudentsController {

    private final DataService dataService;
    private final StudentRegistrationService studentRegistrationService;
    private final StudentService studentService;

    public StudentsController(DataService dataService, StudentRegistrationService studentRegistrationService, StudentService studentService) {
        this.dataService = dataService;
        this.studentRegistrationService = studentRegistrationService;
        this.studentService = studentService;
    }

    @GetMapping("/")
    public String main() {
        return "redirect:/registration";
    }


    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("formData", dataService.execute());
        return "registration_page";
    }

    @PostMapping("/registration")
    public String registration(Model model, @Valid @RequestBody StudentDTO studentDTO) {
        StudentDTO newStudent = TransformationHelper.objectToDto(studentService.save(TransformationHelper.dtoToObject(studentDTO)));
        log.info("newStudent = {}", newStudent);
        model.addAttribute("formData", dataService.execute());
        return "redirect:/registration";
    }

    @GetMapping("/documents")
    public String documents() {
        return "documents_page";
    }


    @GetMapping("/main")
    public String admin() {
        return "admin_main_page";
    }


}
