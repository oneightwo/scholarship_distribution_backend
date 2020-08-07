package com.oneightwo.scholarship_distribution.distribution.reports.services.base;

import com.oneightwo.scholarship_distribution.distribution.reports.models.UniversityReport;
import com.oneightwo.scholarship_distribution.students.models.Student;

import java.util.List;
import java.util.TreeMap;

public interface ScholarshipDistributionByDirectionsAndUniversitiesReportService {

    void setStudents(List<Student> students);

    TreeMap<Long, TreeMap<Long, UniversityReport>> getDataDistributionByUniversitiesInDirections();

    TreeMap<Long, Integer> getAllApplicationsSubmittedToUniversities();

    TreeMap<Long, Integer> getPassedApplicationsSubmittedToUniversities();

    TreeMap<Long, Integer> getExcludedApplicationsSubmittedToUniversities();

    TreeMap<Long, TreeMap<Long, Integer>> getDataDistributionByUniversitiesAndDirections();
}
