package com.oneightwo.scholarship_distribution.distribution.reports.services.impl;

import com.oneightwo.scholarship_distribution.distribution.computing.services.StudentsInDirectionsService;
import com.oneightwo.scholarship_distribution.distribution.reports.services.ScholarshipDistributionByDirectionsReportService;
import com.oneightwo.scholarship_distribution.science_directions.services.ScienceDirectionService;
import com.oneightwo.scholarship_distribution.students.models.Student;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public final class ScholarshipDistributionByDirectionsReport
        extends AbstractScholarshipDistributionReport
        implements ScholarshipDistributionByDirectionsReportService {

    private final TreeMap<String, Integer> numberApplications = new TreeMap<>();
    private final TreeMap<String, Double> averageRatings = new TreeMap<>();
    private final TreeMap<String, Double> minimalRating = new TreeMap<>();
    private final TreeMap<String, Integer> numberPassedApplications = new TreeMap<>();
    private final TreeMap<String, Integer> numberExcludedApplications = new TreeMap<>();
    private final TreeMap<String, Integer> assignedNumberScholarships = new TreeMap<>();

    private final StudentsInDirectionsService studentsInDirectionsService;

    public ScholarshipDistributionByDirectionsReport(ScienceDirectionService scienceDirectionService,
                                                     StudentsInDirectionsService studentsInDirectionsService) {
        super(scienceDirectionService);
        this.studentsInDirectionsService = studentsInDirectionsService;
    }

    @Override
    public void setStudents(List<Student> students) {
        studentsInDirectionsService.setStudents(students);
    }

    private <T> void setNameOnMapKey(Map<Long, T> from, Map<String, T> to) {
        from.forEach((k, v) -> {
            to.put(super.getNameScienceDirectionById(k), v);
        });
    }

    @Override
    public Map<String, Integer> getNumberApplications() {
        setNameOnMapKey(studentsInDirectionsService.getNumberApplications(), numberApplications);
        return numberApplications;
    }

    @Override
    public Map<String, Double> getAverageRatings() {
        setNameOnMapKey(studentsInDirectionsService.getAverageRatings(), averageRatings);
        return averageRatings;
    }

    @Override
    public Map<String, Double> getMinimalRating() {
        setNameOnMapKey(studentsInDirectionsService.getMinimalRatings(), minimalRating);
        return minimalRating;
    }

    @Override
    public Map<String, Integer> getNumberPassedApplications() {
        setNameOnMapKey(studentsInDirectionsService.getQuantityPassedStudents(), numberPassedApplications);
        return numberPassedApplications;
    }

    @Override
    public Map<String, Integer> getNumberExcludedApplications() {
        setNameOnMapKey(studentsInDirectionsService.getQuantityExcludedStudents(), numberExcludedApplications);
        return numberExcludedApplications;
    }

    @Override
    public Map<String, Integer> getAssignedNumberScholarships() {
        setNameOnMapKey(studentsInDirectionsService.getAssignedNumberScholarships(), assignedNumberScholarships);
        return assignedNumberScholarships;
    }
}
