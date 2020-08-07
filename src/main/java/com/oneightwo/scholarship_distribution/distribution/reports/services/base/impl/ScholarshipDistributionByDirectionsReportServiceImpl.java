package com.oneightwo.scholarship_distribution.distribution.reports.services.base.impl;

import com.oneightwo.scholarship_distribution.distribution.calculations.services.StudentsInDirectionsService;
import com.oneightwo.scholarship_distribution.distribution.reports.models.ReportUnit;
import com.oneightwo.scholarship_distribution.distribution.reports.services.base.ScholarshipDistributionByDirectionsReportService;
import com.oneightwo.scholarship_distribution.students.models.Student;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.TreeSet;

@Service
public final class ScholarshipDistributionByDirectionsReportServiceImpl
        implements ScholarshipDistributionByDirectionsReportService {

    private final StudentsInDirectionsService studentsInDirectionsService;

    public ScholarshipDistributionByDirectionsReportServiceImpl(StudentsInDirectionsService studentsInDirectionsService) {
        this.studentsInDirectionsService = studentsInDirectionsService;
    }

    @Override
    public void setStudents(List<Student> students) {
        studentsInDirectionsService.setStudents(students);
    }

    @Override
    public TreeSet<ReportUnit<Long, Integer>> getNumberApplications() {
        return converter(studentsInDirectionsService.getNumberApplications());
    }

    @Override
    public TreeSet<ReportUnit<Long, Double>> getAverageRatings() {
        return converter(studentsInDirectionsService.getAverageRatings());
    }

    @Override
    public TreeSet<ReportUnit<Long, Double>> getMinimalRating() {
        return converter(studentsInDirectionsService.getMinimalRatings());
    }

    @Override
    public TreeSet<ReportUnit<Long, Integer>> getNumberPassedApplications() {
        return converter(studentsInDirectionsService.getQuantityPassedStudents());
    }

    @Override
    public TreeSet<ReportUnit<Long, Integer>> getNumberExcludedApplications() {
        return converter(studentsInDirectionsService.getQuantityExcludedStudents());
    }

    @Override
    public TreeSet<ReportUnit<Long, Integer>> getAssignedNumberScholarships() {
        return converter(studentsInDirectionsService.getAssignedNumberScholarships());
    }

    private <T> TreeSet<ReportUnit<Long, T>> converter(Map<Long, T> from) {
        TreeSet<ReportUnit<Long, T>> to = new TreeSet<>();
        from.forEach((k, v) -> {
            to.add(new ReportUnit<>(k, v));
        });
        return to;
    }
}
