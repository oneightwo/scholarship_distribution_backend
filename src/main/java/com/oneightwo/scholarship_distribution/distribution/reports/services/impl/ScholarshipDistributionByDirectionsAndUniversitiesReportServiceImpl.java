package com.oneightwo.scholarship_distribution.distribution.reports.services.impl;

import com.oneightwo.scholarship_distribution.distribution.computing.services.StudentsInDirectionsAndUniversitiesService;
import com.oneightwo.scholarship_distribution.distribution.reports.models.UniversityReport;
import com.oneightwo.scholarship_distribution.distribution.reports.services.ScholarshipDistributionByDirectionsAndUniversitiesReportService;
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
        extends AbstractScholarshipDistributionReport
        implements ScholarshipDistributionByDirectionsAndUniversitiesReportService {

    private final StudentsInDirectionsAndUniversitiesService studentsInDirectionsAndUniversitiesService;

    private final List<University> universities;

    private final Map<Long, Map<Long, List<Student>>> passedStudentsInDirectionAndUniversity = new TreeMap<>();
    private final Map<Long, Map<Long, List<Student>>> excludedStudentsInDirectionAndUniversity = new TreeMap<>();

    @Autowired
    public ScholarshipDistributionByDirectionsAndUniversitiesReportServiceImpl(ScienceDirectionService scienceDirectionService,
                                                                               UniversityService universityService,
                                                                               StudentsInDirectionsAndUniversitiesService studentsInDirectionsAndUniversitiesService) {
        super(scienceDirectionService);
        this.studentsInDirectionsAndUniversitiesService = studentsInDirectionsAndUniversitiesService;
        universities = universityService.getAll();
    }

    @Override
    public void setStudents(List<Student> students) {
        studentsInDirectionsAndUniversitiesService.setStudents(students);
        passedStudentsInDirectionAndUniversity.putAll(studentsInDirectionsAndUniversitiesService.getPassedStudents().getStudentsInDirectionAndUniversity());
        excludedStudentsInDirectionAndUniversity.putAll(studentsInDirectionsAndUniversitiesService.getExcludedStudents().getStudentsInDirectionAndUniversity());
    }

    @Override
    public TreeMap<String, TreeMap<String, UniversityReport>> getDataDistributionByUniversitiesInDirections() {
        TreeMap<String, TreeMap<String, UniversityReport>> studentsInDirection = new TreeMap<>();
        Map<Long, Map<Long, Integer>> assignedNumberScholarships = studentsInDirectionsAndUniversitiesService.getAssignedNumberScholarships();
        Map<Long, Map<Long, Double>> averageRatings = studentsInDirectionsAndUniversitiesService.getAverageRatings();
        Map<Long, Map<Long, List<Student>>> passedStudents = studentsInDirectionsAndUniversitiesService.getPassedStudents().getStudentsInDirectionAndUniversity();
        passedStudents.forEach((sd, v) -> {
            TreeMap<String, UniversityReport> universityReport = new TreeMap<>();
            v.forEach((u, students) -> {
                universityReport.put(getNameUniversityById(u),
                        new UniversityReport(
                                averageRatings.get(sd).get(u),
                                passedStudents.get(sd).get(u).size(),
                                assignedNumberScholarships.get(sd).get(u))
                );
            });
            studentsInDirection.put(super.getNameScienceDirectionById(sd), universityReport);
        });
        return studentsInDirection;
    }

    private String getNameUniversityById(Long id) {
        for (University university : universities) {
            if (university.getId().equals(id)) {
                return university.getName();
            }
        }
        return "";
    }

    @Override
    public TreeMap<String, Integer> getAllApplicationsSubmittedToUniversities() {
        TreeMap<String, Integer> allApplicationsSubmittedToUniversities = new TreeMap<>();
        setApplications(passedStudentsInDirectionAndUniversity, allApplicationsSubmittedToUniversities);
        setApplications(excludedStudentsInDirectionAndUniversity, allApplicationsSubmittedToUniversities);
        return allApplicationsSubmittedToUniversities;
    }

    @Override
    public TreeMap<String, Integer> getPassedApplicationsSubmittedToUniversities() {
        TreeMap<String, Integer> passedApplicationsSubmittedToUniversities = new TreeMap<>();
        setApplications(passedStudentsInDirectionAndUniversity, passedApplicationsSubmittedToUniversities);
        return passedApplicationsSubmittedToUniversities;
    }

    @Override
    public TreeMap<String, Integer> getExcludedApplicationsSubmittedToUniversities() {
        TreeMap<String, Integer> excludedApplicationsSubmittedToUniversities = new TreeMap<>();
        setApplications(excludedStudentsInDirectionAndUniversity, excludedApplicationsSubmittedToUniversities);
        return excludedApplicationsSubmittedToUniversities;
    }

    private void setApplications(Map<Long, Map<Long, List<Student>>> from, TreeMap<String, Integer> to) {
        from.forEach((sd, v) -> {
            v.forEach((u, students) -> {
                to.merge(getNameUniversityById(u), students.size(), Integer::sum);
            });
        });
    }

    @Override
    public TreeMap<String, TreeMap<String, Integer>> getDataDistributionByUniversitiesAndDirections() {
        TreeMap<String, TreeMap<String, Integer>> dataDistributionByUniversitiesAndDirections = new TreeMap<>();
        studentsInDirectionsAndUniversitiesService.getAssignedNumberScholarships().forEach((sd, v) -> {
            TreeMap<String, Integer> quantityScholarshipInUniversities = new TreeMap<>();
            v.forEach((u, quantity) -> {
                quantityScholarshipInUniversities.put(getNameUniversityById(u), quantity);
            });
            dataDistributionByUniversitiesAndDirections.put(super.getNameScienceDirectionById(sd), quantityScholarshipInUniversities);
        });
        return dataDistributionByUniversitiesAndDirections;
    }
}
