package com.oneightwo.scholarship_distribution.distribution.reports.services.base.impl;

import com.oneightwo.scholarship_distribution.distribution.calculations.services.StudentsInDirectionsAndUniversitiesService;
import com.oneightwo.scholarship_distribution.distribution.reports.models.UniversityReport;
import com.oneightwo.scholarship_distribution.distribution.reports.services.base.ScholarshipDistributionByDirectionsAndUniversitiesReportService;
import com.oneightwo.scholarship_distribution.science_directions.services.ScienceDirectionService;
import com.oneightwo.scholarship_distribution.students.models.Student;
import com.oneightwo.scholarship_distribution.universities.models.University;
import com.oneightwo.scholarship_distribution.universities.services.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public final class ScholarshipDistributionByDirectionsAndUniversitiesReportServiceImpl
        implements ScholarshipDistributionByDirectionsAndUniversitiesReportService {

    private final StudentsInDirectionsAndUniversitiesService studentsInDirectionsAndUniversitiesService;

    private final Map<Long, Map<Long, List<Student>>> passedStudentsInDirectionAndUniversity = new TreeMap<>();
    private final Map<Long, Map<Long, List<Student>>> excludedStudentsInDirectionAndUniversity = new TreeMap<>();

    @Autowired
    public ScholarshipDistributionByDirectionsAndUniversitiesReportServiceImpl(StudentsInDirectionsAndUniversitiesService studentsInDirectionsAndUniversitiesService) {
        this.studentsInDirectionsAndUniversitiesService = studentsInDirectionsAndUniversitiesService;
    }

    @Override
    public void setStudents(List<Student> students) {
        studentsInDirectionsAndUniversitiesService.setStudents(students);
        passedStudentsInDirectionAndUniversity.putAll(studentsInDirectionsAndUniversitiesService.getPassedStudents().getStudentsInDirectionAndUniversity());
        excludedStudentsInDirectionAndUniversity.putAll(studentsInDirectionsAndUniversitiesService.getExcludedStudents().getStudentsInDirectionAndUniversity());
    }

    @Override
    public TreeMap<Long, TreeMap<Long, UniversityReport>> getDataDistributionByUniversitiesInDirections() {
        TreeMap<Long, TreeMap<Long, UniversityReport>> studentsInDirection = new TreeMap<>();
        Map<Long, Map<Long, Integer>> assignedNumberScholarships = studentsInDirectionsAndUniversitiesService.getAssignedNumberScholarships();
        Map<Long, Map<Long, Double>> averageRatings = studentsInDirectionsAndUniversitiesService.getAverageRatings();
        Map<Long, Map<Long, List<Student>>> passedStudents = studentsInDirectionsAndUniversitiesService.getPassedStudents().getStudentsInDirectionAndUniversity();
        passedStudents.forEach((sd, v) -> {
            TreeMap<Long, UniversityReport> universityReport = new TreeMap<>();
            v.forEach((u, students) -> {
                universityReport.put(u,
                        new UniversityReport(
                                averageRatings.get(sd).get(u),
                                passedStudents.get(sd).get(u).size(),
                                assignedNumberScholarships.get(sd).get(u))
                );
            });
            studentsInDirection.put(sd, universityReport);
        });
        return studentsInDirection;
    }

    @Override
    public TreeMap<Long, Integer> getAllApplicationsSubmittedToUniversities() {
        TreeMap<Long, Integer> allApplicationsSubmittedToUniversities = new TreeMap<>();
        setApplications(passedStudentsInDirectionAndUniversity, allApplicationsSubmittedToUniversities);
        setApplications(excludedStudentsInDirectionAndUniversity, allApplicationsSubmittedToUniversities);
        return allApplicationsSubmittedToUniversities;
    }

    @Override
    public TreeMap<Long, Integer> getPassedApplicationsSubmittedToUniversities() {
        TreeMap<Long, Integer> passedApplicationsSubmittedToUniversities = new TreeMap<>();
        setApplications(passedStudentsInDirectionAndUniversity, passedApplicationsSubmittedToUniversities);
        return passedApplicationsSubmittedToUniversities;
    }

    @Override
    public TreeMap<Long, Integer> getExcludedApplicationsSubmittedToUniversities() {
        TreeMap<Long, Integer> excludedApplicationsSubmittedToUniversities = new TreeMap<>();
        setApplications(excludedStudentsInDirectionAndUniversity, excludedApplicationsSubmittedToUniversities);
        return excludedApplicationsSubmittedToUniversities;
    }

    private void setApplications(Map<Long, Map<Long, List<Student>>> from, TreeMap<Long, Integer> to) {
        from.forEach((sd, v) -> {
            v.forEach((u, students) -> {
                to.merge(u, students.size(), Integer::sum);
            });
        });
    }

    @Override
    public TreeMap<Long, TreeMap<Long, Integer>> getDataDistributionByUniversitiesAndDirections() {
        TreeMap<Long, TreeMap<Long, Integer>> dataDistributionByUniversitiesAndDirections = new TreeMap<>();
        studentsInDirectionsAndUniversitiesService.getAssignedNumberScholarships().forEach((sd, v) -> {
            TreeMap<Long, Integer> quantityScholarshipInUniversities = new TreeMap<>();
            v.forEach(quantityScholarshipInUniversities::put);
            dataDistributionByUniversitiesAndDirections.put(sd, quantityScholarshipInUniversities);
        });
        return dataDistributionByUniversitiesAndDirections;
    }
}
