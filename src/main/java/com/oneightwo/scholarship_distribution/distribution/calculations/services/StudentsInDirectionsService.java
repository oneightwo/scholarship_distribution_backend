package com.oneightwo.scholarship_distribution.distribution.calculations.services;

import com.oneightwo.scholarship_distribution.students.models.Student;

import java.util.List;
import java.util.Map;

public interface StudentsInDirectionsService {

    void setStudents(List<Student> students);

    List<Student> getPassedStudents();

    List<Student> getExcludedStudents();

    Map<Long, Double> getMinimalRatings();

    Map<Long, Double> getAverageRatings();

    Map<Long, Integer> getNumberApplications();

    Map<Long, Integer> getQuantityPassedStudents();

    Map<Long, Integer> getQuantityExcludedStudents();

    Map<Long, Integer> getAssignedNumberScholarships();
}
