package com.oneightwo.scholarship_distribution.distribution.calculations.services.impl;

import com.oneightwo.scholarship_distribution.distribution.calculations.operations.DistributionOperation;
import com.oneightwo.scholarship_distribution.distribution.calculations.models.DistributionUnit;
import com.oneightwo.scholarship_distribution.distribution.calculations.services.StudentsInDirectionsService;
import com.oneightwo.scholarship_distribution.data_view.constants.Constants;
import com.oneightwo.scholarship_distribution.students.models.Student;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentsInDirectionsServiceImpl implements StudentsInDirectionsService {

    private final DistributionOperation distributionOperation;
    private DistributionUnit allStudentsInDirections;
    private DistributionUnit passedStudentsInDirections;

    public StudentsInDirectionsServiceImpl(DistributionOperation distributionOperation) {
        this.distributionOperation = distributionOperation;
    }

    @Override
    public void setStudents(List<Student> students) {
        allStudentsInDirections = new DistributionUnit();
        passedStudentsInDirections = new DistributionUnit();
        addStudents(students, allStudentsInDirections);
        addStudents(getPassedStudents(), passedStudentsInDirections);
    }

    private void addStudents(List<Student> students, DistributionUnit appointment) {
        students.forEach(student ->
                appointment.addStudentById(student, student.getScienceDirection().getId())
        );
    }

    @Override
    public List<Student> getPassedStudents() {
        List<Student> students = new ArrayList<>();
        if (isEnoughStudents()) {
            return calculationPassedStudentsBasedOnExcluded();
        } else {
            allStudentsInDirections.getDistributionUnit().values().forEach(students::addAll);
        }
        return students;
    }

    private boolean isEnoughStudents() {
        return calculationPassedStudentsBasedOnExcluded().size() > Constants.NUMBER_SCHOLARSHIPS;
    }

    private List<Student> calculationPassedStudentsBasedOnExcluded() {
        List<Student> students = new ArrayList<>();
        List<Student> excludedStudents = getExcludedStudents();
        allStudentsInDirections.getDistributionUnit().forEach((k, v) -> {
            v.forEach(student -> {
                if (!excludedStudents.contains(student)) {
                    students.add(student);
                }
            });
        });
        return students;
    }

    @Override
    public List<Student> getExcludedStudents() {
        List<Student> students = new ArrayList<>();
        getQuantityExcludedStudents().forEach((k, v) -> {
            List<Student> studentsById = allStudentsInDirections.getStudentsOrDefaultById(k);
            students.addAll(studentsById.stream()
                        .sorted(Comparator.comparingInt(Student::getRating))
                        .collect(Collectors.toList()).subList(0, v));
        });
        System.out.println(students);
        return students;
    }

    @Override
    public Map<Long, Double> getMinimalRatings() {
        Map<Long, Double> minimalRatingInDirections = new HashMap<>();
        allStudentsInDirections.getDistributionUnit().forEach((k, v) -> {
            var sumRating = v.stream().mapToInt(Student::getRating).sum();
            var quantityStudents = v.size();
            minimalRatingInDirections.put(k, (double) sumRating / quantityStudents * Constants.MINIMUM_PERCENTAGE_BORDER);
        });
        return minimalRatingInDirections;
    }

    @Override
    public Map<Long, Double> getAverageRatings() {
        Map<Long, Double> averageRatingInDirections = new HashMap<>();
        allStudentsInDirections.getDistributionUnit().forEach((k, v) -> {
            averageRatingInDirections.put(k, (double) v.stream().mapToInt(Student::getRating).sum() / v.size());
        });
        return averageRatingInDirections;
    }

    @Override
    public Map<Long, Integer> getNumberApplications() {
        Map<Long, Integer> numberApplicationsInDirections = new HashMap<>();
        allStudentsInDirections.getDistributionUnit().forEach((k, v) -> {
            numberApplicationsInDirections.put(k, v.size());
        });
        return numberApplicationsInDirections;
    }

    //TODO it is crap
    @Override
    public Map<Long, Integer> getQuantityExcludedStudents() {
        Map<Long, Integer> excludedStudents = new HashMap<>();
        Map<Long, Double> minimalRatings = getMinimalRatings();
        allStudentsInDirections.getDistributionUnit().forEach((k, v) -> {
            v.forEach(student -> {
                if (student.getRating() < minimalRatings.get(k))
                    excludedStudents.put(k, excludedStudents.getOrDefault(k, 0) + 1);
            });
        });
        return excludedStudents;
    }

    @Override
    public Map<Long, Integer> getQuantityPassedStudents() {
        Map<Long, Integer> passedStudents = new HashMap<>();
        Map<Long, Double> minimalRatings = getMinimalRatings();
        allStudentsInDirections.getDistributionUnit().forEach((k, v) -> {
            v.forEach(student -> {
                if (student.getRating() >= minimalRatings.get(k))
                    passedStudents.put(k, passedStudents.getOrDefault(k, 0) + 1);
            });
        });
        return passedStudents;
    }

    @Override
    public Map<Long, Integer> getAssignedNumberScholarships() {
        return distributionOperation.execute(passedStudentsInDirections, Constants.NUMBER_SCHOLARSHIPS);
    }
}
