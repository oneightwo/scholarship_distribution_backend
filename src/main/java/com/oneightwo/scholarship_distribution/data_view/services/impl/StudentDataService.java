package com.oneightwo.scholarship_distribution.data_view.services.impl;

import com.oneightwo.scholarship_distribution.courses.services.CourseService;
import com.oneightwo.scholarship_distribution.data_view.models.AdditionalInformation;
import com.oneightwo.scholarship_distribution.data_view.models.Stub;
import com.oneightwo.scholarship_distribution.data_view.models.StudentData;
import com.oneightwo.scholarship_distribution.data_view.services.DataService;
import com.oneightwo.scholarship_distribution.science_directions.services.ScienceDirectionService;
import com.oneightwo.scholarship_distribution.students.models.StudentDTO;
import com.oneightwo.scholarship_distribution.students.services.StudentService;
import com.oneightwo.scholarship_distribution.universities.services.UniversityService;
import org.springframework.stereotype.Service;

@Service
public class StudentDataService implements DataService<StudentData, Stub> {

    private final UniversityService universityService;
    private final ScienceDirectionService scienceDirectionService;
    private final CourseService courseService;
    private final StudentService studentService;
//    private AdditionalInformation additionalInformation;

    public StudentDataService(UniversityService universityService, ScienceDirectionService scienceDirectionService, CourseService courseService, StudentService studentService) {
        this.universityService = universityService;
        this.scienceDirectionService = scienceDirectionService;
        this.courseService = courseService;
        this.studentService = studentService;
    }

    @Override
    public StudentData execute(Stub stub) {
        return null;
    }
}
