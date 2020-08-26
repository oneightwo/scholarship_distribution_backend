package com.oneightwo.scholarship_distribution.controllers;

import com.oneightwo.scholarship_distribution.core.helpers.TransformationHelper;
import com.oneightwo.scholarship_distribution.courses.services.CourseService;
import com.oneightwo.scholarship_distribution.science_directions.services.ScienceDirectionService;
import com.oneightwo.scholarship_distribution.students.services.StudentService;
import com.oneightwo.scholarship_distribution.universities.services.UniversityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequestMapping("/admin")
@Controller
public class ViewAdminController {

    private final StudentService studentService;
    private final UniversityService universityService;
    private final ScienceDirectionService scienceDirectionService;
    private final CourseService courseService;

    public ViewAdminController(StudentService studentService, UniversityService universityService, ScienceDirectionService scienceDirectionService, CourseService courseService) {
        this.studentService = studentService;
        this.universityService = universityService;
        this.scienceDirectionService = scienceDirectionService;
        this.courseService = courseService;
    }

    @GetMapping("/main")
    public String main(Model model) {
//        model.addAttribute("students", TransformationHelper.studentsToDtos(studentService.getAll()));
        return "admin_main_page";
    }

    @GetMapping("/participants")
    public String participants(Model model) {
        model.addAttribute("students", TransformationHelper.studentsToDtos(studentService.getAll()));
        model.addAttribute("universities", TransformationHelper.universitiesToDtos(universityService.getCurrent()));
        model.addAttribute("scienceDirections", TransformationHelper.scienceDirectionsToDtos(scienceDirectionService.getCurrent()));
        model.addAttribute("courses", TransformationHelper.coursesToDtos(courseService.getCurrent()));
        return "admin_participants_page";
    }
}
