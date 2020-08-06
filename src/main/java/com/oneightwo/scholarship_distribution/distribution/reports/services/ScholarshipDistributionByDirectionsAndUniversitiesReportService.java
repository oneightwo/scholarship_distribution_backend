package com.oneightwo.scholarship_distribution.distribution.reports.services;

import com.oneightwo.scholarship_distribution.distribution.reports.models.UniversityReport;
import com.oneightwo.scholarship_distribution.students.models.Student;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public interface ScholarshipDistributionByDirectionsAndUniversitiesReportService {

    void setStudents(List<Student> students);

    TreeMap<String, TreeMap<String, UniversityReport>> getDataDistributionByUniversitiesInDirections();

    TreeMap<String, Integer> getAllApplicationsSubmittedToUniversities();

    TreeMap<String, Integer> getPassedApplicationsSubmittedToUniversities();

    TreeMap<String, Integer> getExcludedApplicationsSubmittedToUniversities();

    TreeMap<String, TreeMap<String, Integer>> getDataDistributionByUniversitiesAndDirections();
}
