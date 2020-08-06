package com.oneightwo.scholarship_distribution.courses.services;

import com.oneightwo.scholarship_distribution.courses.models.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    Optional<Course> getById(Long id);

    List<Course> getAll();

    List<Course> findByNumber();
}
