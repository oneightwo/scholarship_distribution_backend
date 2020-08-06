package com.oneightwo.scholarship_distribution.students.services;

import com.oneightwo.scholarship_distribution.distribution.constants.Semester;
import com.oneightwo.scholarship_distribution.core.exceptions.CoreException;
import com.oneightwo.scholarship_distribution.students.models.Student;
import com.oneightwo.scholarship_distribution.students.models.StudentDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentService {

    @Deprecated
    List<Student> getAll();

    Student getById(Long id) throws CoreException;

    Student save(Student student);

    void delete(Long id) throws CoreException;

    Student update(Student student) throws CoreException;

    List<Student> getStudentsBySemesterAndYear(Semester semester, int year);

    List<Student> getStudentsBySemesterAndYear(Semester semester, int year, Pageable pageable);
}
