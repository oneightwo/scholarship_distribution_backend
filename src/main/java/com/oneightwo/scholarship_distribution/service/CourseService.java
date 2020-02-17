package com.oneightwo.scholarship_distribution.service;

import com.oneightwo.scholarship_distribution.model.Course;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface CourseService {
    Optional<Course> getById(BigInteger id);

    List<Course> getAll();

    List<Course> findByNumber();
}
