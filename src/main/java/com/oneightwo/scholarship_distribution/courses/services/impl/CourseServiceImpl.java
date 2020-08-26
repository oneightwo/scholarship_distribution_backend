package com.oneightwo.scholarship_distribution.courses.services.impl;

import com.oneightwo.scholarship_distribution.courses.models.Course;
import com.oneightwo.scholarship_distribution.courses.services.CourseService;
import com.oneightwo.scholarship_distribution.distribution.constants.Semester;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Override
    public List<Course> getCurrent() {
        Semester currentSemester = Semester.getSemesterByDate(LocalDate.now());
        if (currentSemester.equals(Semester.AUTUMN)) {
            return List.of(Course.FIRST, Course.SECOND, Course.THIRD);
        } else {
            return List.of(Course.FIRST, Course.SECOND);
        }
    }

    @Override
    public List<Course> getByPeriod(LocalDate date) {
        return null;
    }
}
