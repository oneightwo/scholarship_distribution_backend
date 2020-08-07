package com.oneightwo.scholarship_distribution.distribution.calculations.services.impl;

import com.oneightwo.scholarship_distribution.distribution.calculations.models.ExcludedStudents;
import com.oneightwo.scholarship_distribution.distribution.calculations.models.PassedStudents;
import com.oneightwo.scholarship_distribution.distribution.calculations.operations.DistributionOperation;
import com.oneightwo.scholarship_distribution.distribution.calculations.services.StudentsInDirectionsAndUniversitiesService;
import com.oneightwo.scholarship_distribution.distribution.calculations.services.StudentsInDirectionsService;
import com.oneightwo.scholarship_distribution.students.models.Student;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentsInDirectionsAndUniversitiesServiceImpl implements StudentsInDirectionsAndUniversitiesService {

    private final DistributionOperation distributionOperation;
    private final StudentsInDirectionsService studentsInDirectionsService;

    private PassedStudents passedStudents;
    private ExcludedStudents excludedStudents;

    public StudentsInDirectionsAndUniversitiesServiceImpl(DistributionOperation distributionOperation,
                                                          StudentsInDirectionsService studentsInDirectionsService) {
        this.distributionOperation = distributionOperation;
        this.studentsInDirectionsService = studentsInDirectionsService;
    }

    @Override
    public void setStudents(List<Student> students) {
        studentsInDirectionsService.setStudents(students);
        passedStudents = new PassedStudents(studentsInDirectionsService.getPassedStudents());
        excludedStudents = new ExcludedStudents(studentsInDirectionsService.getExcludedStudents());
    }

    @Override
    public ExcludedStudents getExcludedStudents() {
        return excludedStudents;
    }

    @Override
    public PassedStudents getPassedStudents() {
        return passedStudents;
    }

    @Override
    public Map<Long, Map<Long, Integer>> getAssignedNumberScholarships() {
        Map<Long, Map<Long, Integer>> result = new HashMap<>();
        Map<Long, Integer> assignedNumberScholarships = studentsInDirectionsService.getAssignedNumberScholarships();
        passedStudents.getStudentsInDirectionsAndDistributionUnit().forEach((k, v) -> {
            result.put(k, distributionOperation.execute(v, assignedNumberScholarships.get(k)));
        });
        return result;
    }

    @Override
    public Map<Long, Map<Long, Double>> getAverageRatings() {
        Map<Long, Map<Long, Double>> averageRatingInDirections = new HashMap<>();
        passedStudents.getStudentsInDirectionAndUniversity().forEach((sd, v) -> {
            v.forEach((u, students) -> {
                averageRatingInDirections.put(sd, new HashMap<>(){{
                    put(u, (double) students.stream().mapToInt(Student::getRating).sum() / students.size());
                }});
            });
        });
        return averageRatingInDirections;
    }

}
