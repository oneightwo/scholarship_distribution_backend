package com.oneightwo.scholarship_distribution.distribution.reports.services.base.impl;

import com.oneightwo.scholarship_distribution.distribution.calculations.services.StudentsInDirectionsService;
import com.oneightwo.scholarship_distribution.distribution.reports.services.base.ScholarshipDistributionByDirectionsReportService;
import com.oneightwo.scholarship_distribution.science_directions.services.ScienceDirectionService;
import com.oneightwo.scholarship_distribution.students.models.Student;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.TreeMap;

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
    public TreeMap<Long, Integer> getNumberApplications() {
        return new TreeMap<>(studentsInDirectionsService.getNumberApplications());
    }

    @Override
    public TreeMap<Long, Double> getAverageRatings() {
        return new TreeMap<>(studentsInDirectionsService.getAverageRatings());
    }

    @Override
    public TreeMap<Long, Double> getMinimalRating() {
        return new TreeMap<>(studentsInDirectionsService.getMinimalRatings());
    }

    @Override
    public TreeMap<Long, Integer> getNumberPassedApplications() {
        return new TreeMap<>(studentsInDirectionsService.getQuantityPassedStudents());
    }

    @Override
    public TreeMap<Long, Integer> getNumberExcludedApplications() {
        return new TreeMap<>(studentsInDirectionsService.getQuantityExcludedStudents());
    }

    @Override
    public TreeMap<Long, Integer> getAssignedNumberScholarships() {
        return new TreeMap<>(studentsInDirectionsService.getAssignedNumberScholarships());
    }
}
