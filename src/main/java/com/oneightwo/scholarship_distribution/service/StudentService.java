package com.oneightwo.scholarship_distribution.service;

import com.oneightwo.scholarship_distribution.model.Student;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface StudentService {
    Optional<Student> getById(BigInteger id);

    List<Student> getAll();

    Student save(Student student);

    boolean deleteById(BigInteger id);

    Student update(Student student);

    boolean isConsist(Student student);

    List<Student> getStudentByMonthsAndYear(List<Integer> months, int year);
}
