package com.oneightwo.scholarship_distribution.distribution.reports.services.base.impl;

import com.oneightwo.scholarship_distribution.distribution.calculations.services.StudentsInDirectionsAndUniversitiesService;
import com.oneightwo.scholarship_distribution.distribution.reports.models.ReportUnit;
import com.oneightwo.scholarship_distribution.distribution.reports.models.UniversityReport;
import com.oneightwo.scholarship_distribution.distribution.reports.services.base.ScholarshipDistributionByDirectionsAndUniversitiesReportService;
import com.oneightwo.scholarship_distribution.students.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public final class ScholarshipDistributionByDirectionsAndUniversitiesReportServiceImpl
        implements ScholarshipDistributionByDirectionsAndUniversitiesReportService {

    private final StudentsInDirectionsAndUniversitiesService studentsInDirectionsAndUniversitiesService;

    private Map<Long, Map<Long, List<Student>>> passedStudentsInDirectionAndUniversity;
    private Map<Long, Map<Long, List<Student>>> excludedStudentsInDirectionAndUniversity;

    @Autowired
    public ScholarshipDistributionByDirectionsAndUniversitiesReportServiceImpl(StudentsInDirectionsAndUniversitiesService studentsInDirectionsAndUniversitiesService) {
        this.studentsInDirectionsAndUniversitiesService = studentsInDirectionsAndUniversitiesService;
    }

    @Override
    public void setStudents(List<Student> students) {
        studentsInDirectionsAndUniversitiesService.setStudents(students);
        passedStudentsInDirectionAndUniversity = new TreeMap<>(studentsInDirectionsAndUniversitiesService.getPassedStudents().getStudentsInDirectionAndUniversity());
        excludedStudentsInDirectionAndUniversity = new TreeMap<>(studentsInDirectionsAndUniversitiesService.getExcludedStudents().getStudentsInDirectionAndUniversity());
    }

    @Override
    public TreeSet<ReportUnit<Long, TreeSet<ReportUnit<Long, UniversityReport>>>> getDataDistributionByUniversitiesInDirections() {
        TreeSet<ReportUnit<Long, TreeSet<ReportUnit<Long, UniversityReport>>>> studentsInDirection = new TreeSet<>();
        Map<Long, Map<Long, Integer>> assignedNumberScholarships = studentsInDirectionsAndUniversitiesService.getAssignedNumberScholarships();
        Map<Long, Map<Long, Double>> averageRatings = studentsInDirectionsAndUniversitiesService.getAverageRatings();
        Map<Long, Map<Long, List<Student>>> passedStudents = studentsInDirectionsAndUniversitiesService.getPassedStudents().getStudentsInDirectionAndUniversity();
        passedStudents.forEach((sd, v) -> {
            ReportUnit<Long, TreeSet<ReportUnit<Long, UniversityReport>>> tempSd;
            TreeSet<ReportUnit<Long, UniversityReport>> tempU = new TreeSet<>();
            v.forEach((u, students) -> {
                tempU.add(new ReportUnit<>(u, new UniversityReport(
                        averageRatings.get(sd).get(u),
                        passedStudents.get(sd).get(u).size(),
                        assignedNumberScholarships.get(sd).get(u)))
                );
            });
            tempSd = new ReportUnit<>(sd, tempU);
            studentsInDirection.add(tempSd);
        });
        return studentsInDirection;
    }

    @Override
    public TreeSet<ReportUnit<Long, Integer>> getAllApplicationsSubmittedToUniversities() {
        return converter(unionPassedAndExcludedStudents(passedStudentsInDirectionAndUniversity, excludedStudentsInDirectionAndUniversity));
    }

    private Map<Long, Map<Long, List<Student>>> unionPassedAndExcludedStudents(Map<Long, Map<Long, List<Student>>> passed,
                                                                               Map<Long, Map<Long, List<Student>>> excluded) {

        Map<Long, Map<Long, List<Student>>> result = new HashMap<>() {{
            passed.forEach((sd, v) -> {
                put(sd, new HashMap<>() {{
                    v.forEach((u, sList) -> {
                        put(u, List.copyOf(sList));
                    });
                }});
            });
        }};
        excluded.forEach((sd, v) -> {
            v.forEach((u, students) -> {
                List<Student> studentsList = new ArrayList<>(result.get(sd).get(u));
                studentsList.addAll(students);
                result.get(sd).put(u, studentsList);
            });
        });
        return result;
    }

    @Override
    public TreeSet<ReportUnit<Long, Integer>> getPassedApplicationsSubmittedToUniversities() {
        return converter(passedStudentsInDirectionAndUniversity);
    }

    @Override
    public TreeSet<ReportUnit<Long, Integer>> getExcludedApplicationsSubmittedToUniversities() {
        return converter(excludedStudentsInDirectionAndUniversity);
    }

    private TreeSet<ReportUnit<Long, Integer>> converter(Map<Long, Map<Long, List<Student>>> from) {
        TreeSet<ReportUnit<Long, Integer>> to = new TreeSet<>();
        Map<Long, Integer> temp = new HashMap<>();
        from.forEach((sd, v) -> {
            v.forEach((u, students) -> {
                temp.merge(u, students.size(), Integer::sum);
            });
        });
        temp.forEach((k, v) -> {
            to.add(new ReportUnit<>(k, v));
        });
        return to;
    }

    @Override // TreeMap<Long, TreeMap<Long, Integer>>
    public TreeSet<ReportUnit<Long, TreeSet<ReportUnit<Long, Integer>>>> getDataDistributionByUniversitiesAndDirections() {
        TreeSet<ReportUnit<Long, TreeSet<ReportUnit<Long, Integer>>>> dataDistributionByUniversitiesAndDirections = new TreeSet<>();
        studentsInDirectionsAndUniversitiesService.getAssignedNumberScholarships().forEach((sd, v) -> {
            TreeSet<ReportUnit<Long, Integer>> temp = new TreeSet<>();
            v.forEach((u, val) -> {
                temp.add(new ReportUnit<>(u, val));
            });
            dataDistributionByUniversitiesAndDirections.add(new ReportUnit<>(sd, temp));
        });
        return dataDistributionByUniversitiesAndDirections;
    }
}
