package com.oneightwo.scholarship_distribution.distribution.computing.services;

import com.oneightwo.scholarship_distribution.distribution.computing.models.ExcludedStudents;
import com.oneightwo.scholarship_distribution.distribution.computing.models.PassedStudents;
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
