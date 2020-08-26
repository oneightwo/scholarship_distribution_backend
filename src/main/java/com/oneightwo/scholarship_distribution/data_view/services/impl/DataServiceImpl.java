package com.oneightwo.scholarship_distribution.data_view.services.impl;

import com.oneightwo.scholarship_distribution.core.exceptions.CoreException;
import com.oneightwo.scholarship_distribution.core.helpers.TransformationHelper;
import com.oneightwo.scholarship_distribution.courses.services.CourseService;
import com.oneightwo.scholarship_distribution.data_view.models.FormData;
import com.oneightwo.scholarship_distribution.data_view.services.DataService;
import com.oneightwo.scholarship_distribution.science_directions.services.ScienceDirectionService;
import com.oneightwo.scholarship_distribution.students.models.StudentDTO;
import com.oneightwo.scholarship_distribution.students.services.StudentService;
import com.oneightwo.scholarship_distribution.universities.services.UniversityService;
import org.springframework.stereotype.Service;

import java.util.TreeSet;

@Service
public class DataServiceImpl implements DataService {

    private final UniversityService universityService;
    private final ScienceDirectionService scienceDirectionService;
    private final CourseService courseService;
    private final StudentService studentService;
    private FormData formData;

    public DataServiceImpl(UniversityService universityService, ScienceDirectionService scienceDirectionService, CourseService courseService, StudentService studentService) {
        this.universityService = universityService;
        this.scienceDirectionService = scienceDirectionService;
        this.courseService = courseService;
        this.studentService = studentService;
    }

    @Override
    public FormData execute() {
        formData = new FormData();
        setCourses();
        setUniversities();
        setScienceDirections();
        return formData;
    }

    @Override
    public FormData execute(Long studentId) {
        formData = new FormData();
        setCourses();
        setUniversities();
        setScienceDirections();
        try {
            formData.setStudent(TransformationHelper.objectToDto(studentService.getById(studentId)));
        } catch (CoreException e) {
            formData.setStudent(new StudentDTO());
        }
        return formData;
    }

    private void setCourses() {
        formData.setCourses(new TreeSet<>(TransformationHelper.coursesToDtos(courseService.getCurrent())));
    }

    private void setUniversities() {
        formData.setUniversities(new TreeSet<>(
                TransformationHelper.universitiesToDtos(universityService.getCurrent())));
    }


    private void setScienceDirections() {
        formData.setScienceDirections(new TreeSet<>(
                TransformationHelper.scienceDirectionsToDtos(scienceDirectionService.getCurrent())));
    }


}
