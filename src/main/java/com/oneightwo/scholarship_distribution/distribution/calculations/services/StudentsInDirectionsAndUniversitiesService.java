package com.oneightwo.scholarship_distribution.distribution.calculations.services;

import com.oneightwo.scholarship_distribution.distribution.calculations.models.ExcludedStudents;
import com.oneightwo.scholarship_distribution.distribution.calculations.models.PassedStudents;
import com.oneightwo.scholarship_distribution.students.models.Student;

import java.util.List;
import java.util.Map;

public interface StudentsInDirectionsAndUniversitiesService {

    void setStudents(List<Student> students);

    ExcludedStudents getExcludedStudents();

    PassedStudents getPassedStudents();

    Map<Long, Map<Long, Integer>> getAssignedNumberScholarships();

    Map<Long, Map<Long, Double>> getAverageRatings();

}
