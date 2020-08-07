package com.oneightwo.scholarship_distribution.distribution.reports.services.base;

import com.oneightwo.scholarship_distribution.students.models.Student;

import java.util.List;
import java.util.TreeMap;

public interface ScholarshipDistributionByDirectionsReportService {

    void setStudents(List<Student> students);

    TreeMap<Long, Integer> getNumberApplications();

    TreeMap<Long, Double> getAverageRatings();

    TreeMap<Long, Double> getMinimalRating();

    TreeMap<Long, Integer> getNumberPassedApplications();

    TreeMap<Long, Integer> getNumberExcludedApplications();

    TreeMap<Long, Integer> getAssignedNumberScholarships();
}
