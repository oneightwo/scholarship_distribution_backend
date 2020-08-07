package com.oneightwo.scholarship_distribution.distribution.reports.services.base;

import com.oneightwo.scholarship_distribution.distribution.reports.models.ReportUnit;
import com.oneightwo.scholarship_distribution.distribution.reports.models.UniversityReport;
import com.oneightwo.scholarship_distribution.students.models.Student;

import java.util.List;
import java.util.TreeSet;

public interface ScholarshipDistributionByDirectionsAndUniversitiesReportService {

    void setStudents(List<Student> students);

    TreeSet<ReportUnit<Long, TreeSet<ReportUnit<Long, UniversityReport>>>> getDataDistributionByUniversitiesInDirections();

    TreeSet<ReportUnit<Long, Integer>> getAllApplicationsSubmittedToUniversities();

    TreeSet<ReportUnit<Long, Integer>> getPassedApplicationsSubmittedToUniversities();

    TreeSet<ReportUnit<Long, Integer>> getExcludedApplicationsSubmittedToUniversities();

    TreeSet<ReportUnit<Long, TreeSet<ReportUnit<Long, Integer>>>> getDataDistributionByUniversitiesAndDirections();
}
