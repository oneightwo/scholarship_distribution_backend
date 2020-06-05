package com.oneightwo.scholarship_distribution.service;

import com.oneightwo.scholarship_distribution.constants.Semester;
import com.oneightwo.scholarship_distribution.core.exceptions.CoreException;
import com.oneightwo.scholarship_distribution.model.Student;
import org.springframework.data.domain.Pageable;

import java.math.BigInteger;
import java.util.List;

public interface StudentService {

    Student getById(BigInteger id) throws CoreException;

    Student save(Student student);

    void deleteById(BigInteger id) throws CoreException;

    Student update(Student student) throws CoreException;

    @Deprecated
    boolean isConsist(Student student);

    List<Student> getStudentBySemesterAndYear(Semester semester, int year);

    List<Student> getStudentBySemesterAndYear(Semester semester, int year, Pageable pageable);
}
