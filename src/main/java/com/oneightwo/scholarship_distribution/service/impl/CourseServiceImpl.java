package com.oneightwo.scholarship_distribution.service.impl;

import com.oneightwo.scholarship_distribution.model.Course;
import com.oneightwo.scholarship_distribution.repository.CourseRepository;
import com.oneightwo.scholarship_distribution.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    private Logger log = LoggerFactory.getLogger(CourseServiceImpl.class);

    @Override
    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    @Override
    public Optional<Course> getById(BigInteger id) {
        return courseRepository.findById(id);
    }

    @Override
    public List<Course> findByNumber() {
        log.info("month = {}", LocalDate.now().getMonthValue());
        return LocalDate.now().getMonthValue() < 9 ?
                courseRepository.findByNumbers(Arrays.asList(1, 2)) :
                courseRepository.findByNumbers(Arrays.asList(1, 2, 3));
    }
}
