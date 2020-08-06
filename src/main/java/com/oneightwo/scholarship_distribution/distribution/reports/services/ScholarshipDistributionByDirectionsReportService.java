package com.oneightwo.scholarship_distribution.distribution.reports.services;

import com.oneightwo.scholarship_distribution.students.models.Student;

import java.util.List;
import java.util.Map;

public interface ScholarshipDistributionByDirectionsReportService {

    void setStudents(List<Student> students);

    Map<String, Integer> getNumberApplications();

    Map<String, Double> getAverageRatings();

    Map<String, Double> getMinimalRating();

    Map<String, Integer> getNumberPassedApplications();

    Map<String, Integer> getNumberExcludedApplications();

    Map<String, Integer> getAssignedNumberScholarships();
}
