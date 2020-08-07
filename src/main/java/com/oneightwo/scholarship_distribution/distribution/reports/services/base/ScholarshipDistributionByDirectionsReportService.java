package com.oneightwo.scholarship_distribution.distribution.reports.services.base;

import com.oneightwo.scholarship_distribution.distribution.reports.models.ReportUnit;
import com.oneightwo.scholarship_distribution.students.models.Student;

import java.util.List;
import java.util.TreeSet;

public interface ScholarshipDistributionByDirectionsReportService {

    void setStudents(List<Student> students);

    TreeSet<ReportUnit<Long, Integer>> getNumberApplications();

    TreeSet<ReportUnit<Long, Double>> getAverageRatings();

    TreeSet<ReportUnit<Long, Double>> getMinimalRating();

    TreeSet<ReportUnit<Long, Integer>> getNumberPassedApplications();

    TreeSet<ReportUnit<Long, Integer>> getNumberExcludedApplications();

    TreeSet<ReportUnit<Long, Integer>> getAssignedNumberScholarships();
}
