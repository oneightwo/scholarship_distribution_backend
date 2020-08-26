package com.oneightwo.scholarship_distribution.courses.services;

import com.oneightwo.scholarship_distribution.courses.models.Course;

import java.time.LocalDate;
import java.util.List;

public interface CourseService {

    List<Course> getCurrent();

    List<Course> getByPeriod(LocalDate date);
}
